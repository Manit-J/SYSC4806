package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class AccessingBuddyData {

    private static final Logger log = LoggerFactory.getLogger(AccessingBuddyData.class);

    public static void main(String[] args) {
        SpringApplication.run(AccessingBuddyData.class, args);
    }

    @Bean
    public CommandLineRunner demo(BuddyInfoRepo buddyRepo, AddressBookRepo addressBookRepo) {
        return (args) -> {

            // Create an AddressBook
            AddressBook addressBook = new AddressBook();

            // Create buddies and add them to the address book
            BuddyInfo jack = new BuddyInfo("Jack", "123", "849244577");
            BuddyInfo chloe = new BuddyInfo("Chloe", "Some street", "21840350325");
            BuddyInfo kim = new BuddyInfo("Kim", "This city", "2243058024356");
            BuddyInfo david = new BuddyInfo("David", "That house", "32507327507");
            BuddyInfo michelle = new BuddyInfo("Michelle", "Wow area", "23095735705");

            // Add buddies to the address book using addBuddy (sets the bidirectional link)
            addressBook.addBuddy(jack);
            addressBook.addBuddy(chloe);
            addressBook.addBuddy(kim);
            addressBook.addBuddy(david);
            addressBook.addBuddy(michelle);

            // Save the address book (cascade will save all buddies)
            addressBookRepo.save(addressBook);

            // Fetch all buddies from the repository
            log.info("Buddies found with findAll():");
            log.info("-------------------------------");
            buddyRepo.findAll().forEach(b -> log.info(b.toString()));
            log.info("");

            // Fetch a single buddy by ID
            BuddyInfo buddy = buddyRepo.findById(1);
            log.info("Buddy found with findById(1):");
            log.info("--------------------------------");
            log.info(buddy.toString());
            log.info("");

            // Fetch buddies by name
            log.info("Buddy found with findByName('Kim'):");
            log.info("--------------------------------------------");
            buddyRepo.findByName("Kim").forEach(k -> log.info(k.toString()));
            log.info("");

            // Fetch the address book
            Optional<AddressBook> fetchedBook = addressBookRepo.findById(addressBook.getId());
            log.info("Address Book found with findById(1):");
            log.info("--------------------------------------------");
            log.info("Found");
            log.info("");
        };
    }

}


