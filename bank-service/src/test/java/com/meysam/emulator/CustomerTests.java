package com.meysam.emulator;

import com.meysam.emulator.card.model.Customer;
import com.meysam.emulator.card.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerTests {
    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void saveCustomer() {
        saveCustomer("fatherName", "myFingerPrint", "myLastName", "myFirstName", "nationalCode");
    }

    private void saveCustomer(String fatherName, String finger, String lastName, String firstName, String nCode) {
        Customer customer = new Customer();
        customer.setFatherName(fatherName);
        customer.setFingerPrint(finger);
        customer.setLastName(lastName);
        customer.setFirstName(firstName);
        customer.setNationalCode(nCode);
        customerRepository.save(customer);
    }
}
