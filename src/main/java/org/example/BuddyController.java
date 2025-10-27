package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
public class BuddyController {

    @Autowired
    BuddyInfoRepo buddyRepo;

    @Autowired
    AddressBookRepo bookRepo;

    @GetMapping("/addBuddy/{addressBookId}")
    public String addBuddy(@PathVariable Long addressBookId, Model model) {
        AddressBook book = bookRepo.findById(addressBookId).orElse(null);
        if (book == null) {
            model.addAttribute("error", "Address Book not found");
            return "error";
        }

        BuddyInfo buddy = new BuddyInfo();
        buddy.setAddressBook(book); // link buddy to this book
        model.addAttribute("buddy", buddy);
        model.addAttribute("addressBookId", addressBookId);
        return "addBuddy";
    }

    @PostMapping("/addBuddy")
    public String addBuddyForm(@ModelAttribute BuddyInfo buddy,
                               @RequestParam Long addressBookId,
                               Model model) {
        AddressBook addressBook = bookRepo.findById(addressBookId).orElse(null);
        buddy.setAddressBook(addressBook);
        buddyRepo.save(buddy);

        model.addAttribute("addressBook", addressBook);
        return "createAddressBook";
    }
}
