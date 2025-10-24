package org.example;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class BuddyInfo {

    @Id
    @GeneratedValue
    private int id;

    private String name;
    private String address;
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "address_book_id")
    private AddressBook addressBook;

    public AddressBook getAddressBook() {
        return addressBook;
    }

    public void setAddressBook(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    public BuddyInfo(String buddyName, String buddyAddress, String buddyPhoneNumber) {
        name = buddyName;
        address = buddyAddress;
        phoneNumber = buddyPhoneNumber;
    }

    public BuddyInfo() {
    }

    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress(){
        return address;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setId(int buddyId) {
        id = buddyId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "id: "+ id + ", name: "+ name + ", address: "+ address + ", phoneNumber: "+ phoneNumber;
    }

    public static void main(String[] args) {
        BuddyInfo buddyInfo = new BuddyInfo("Homer", "Homer's Address", "8888888888");
        System.out.println("Hello " + buddyInfo.getName());
    }
}
