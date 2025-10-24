package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
}
