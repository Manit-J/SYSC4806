package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class BuddyInfoTest {

    @Test
    public void getName() {
        BuddyInfo buddy = new BuddyInfo("Ant", "Ant's Address", "98293894435");
        assertEquals("Ant", buddy.getName());
    }

    @Test
    public void getAddress() {
        BuddyInfo buddy = new BuddyInfo("Ant", "Ant's Address", "98293894435");
        assertEquals("Ant's Address", buddy.getAddress());
    }
}