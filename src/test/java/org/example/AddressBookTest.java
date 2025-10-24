package org.example;

import static org.junit.Assert.*;

public class AddressBookTest {

    @org.junit.Test
    public void addBuddy() {
        BuddyInfo buddy1 = new BuddyInfo("John", "123 Street in Some City", "9090909090");
        AddressBook addressBook = new AddressBook();
        addressBook.addBuddy(buddy1);
        //assertEquals(addressBook.getAddressBook().get(buddy1), buddy1.getAddress());

    }

    @org.junit.Test
    public void removeBuddy() {

        BuddyInfo buddy1 = new BuddyInfo("John", "123 Street in Some City", "9090909090");
        AddressBook addressBook = new AddressBook();
        addressBook.addBuddy(buddy1);
        addressBook.removeBuddy(buddy1);
        assertEquals(addressBook.getMyBuddies().size(), 0);

    }
}