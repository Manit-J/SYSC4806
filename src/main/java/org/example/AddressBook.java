package org.example;
import java.io.Serializable;
import java.util.*;

import jakarta.persistence.*;
import org.springframework.data.rest.core.annotation.RestResource;

@Entity
public class AddressBook {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "addressBook", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BuddyInfo> myBuddies;


    public AddressBook() {
        myBuddies = new ArrayList<>();
    }

    public void addBuddy(BuddyInfo buddy) {
        myBuddies.add(buddy);
        buddy.setAddressBook(this);
    }

    public void removeBuddy(BuddyInfo buddy){
        myBuddies.remove(buddy);
        buddy.setAddressBook(null);
    }

    @Override
    public String toString() {
        return "AddressBook [id=" + id + ", addressBook=" + myBuddies + "]";
    }

    public static void main(String[] args) {
        System.out.println("Address Book");
        BuddyInfo myBuddy = new BuddyInfo("John", "Ottawa", "8989891881");
        AddressBook myBook = new AddressBook();
        myBook.addBuddy(myBuddy);
        myBook.removeBuddy(myBuddy);
    }


    public void setMyBuddies(List<BuddyInfo> addressBook) {
        this.myBuddies = addressBook;
    }

    public List<BuddyInfo> getMyBuddies() {
        return this.myBuddies;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
