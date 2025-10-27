package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AddressBookController {

    @Autowired
    private AddressBookRepo addressBookRepository;

    @GetMapping("/addressBooks/{id}")
    public String viewAddressBook(@PathVariable Long id, Model model) {
        AddressBook addressBook = addressBookRepository.findById(id).orElse(null);
        if (addressBook == null) {
            model.addAttribute("error", "AddressBook not found");
            return "error"; // optional error page
        }

        model.addAttribute("addressBook", addressBook);
        model.addAttribute("buddies", addressBook.getMyBuddies());
        return "addressBookView"; // the Thymeleaf template name
    }

    @GetMapping("/viewAllAddressBooks")
    public String allAddressBooks(Model model) {
        List<AddressBook> allBooks = addressBookRepository.findAll();
        model.addAttribute("allBooks", allBooks);
        return "allAddressBooks";
    }

    @GetMapping("/createAddressBook")
    public String createAddressBook(Model model) {
        AddressBook newAddressBook = new AddressBook();
        addressBookRepository.save(newAddressBook);
        model.addAttribute("addressBook", newAddressBook);
        return "createAddressBook";
    }
}
