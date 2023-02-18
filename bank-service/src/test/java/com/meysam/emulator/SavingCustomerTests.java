package com.meysam.emulator;

import com.meysam.emulator.card.model.Customer;
import com.meysam.emulator.card.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SavingCustomerTests {
    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void saveCustomer_duplicatedNcode_failes() {
        Customer customer = new Customer();
        customer.setFatherName("fatherName");
        customer.setFingerPrint("myFin");
        customer.setLastName("myLast");
        customer.setFirstName("firstName");
        customer.setNationalCode("55845");
        customerRepository.save(customer);

        Customer customer1 = new Customer();
        customer1.setFatherName("fatherName");
        customer1.setFingerPrint("myFin");
        customer1.setLastName("myLast");
        customer1.setFirstName("firstName");
        customer1.setNationalCode("55845");
        customerRepository.save(customer1);
    }

}
