package org.example;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class WebAppTests {

    @Autowired
    private GreetingController greetingController;

    @Autowired
    private AddressBookController addressBookController;

    @Test
    void contextLoads() throws Exception {
        assertThat(greetingController).isNotNull();
        assertThat(addressBookController).isNotNull();
    }

}