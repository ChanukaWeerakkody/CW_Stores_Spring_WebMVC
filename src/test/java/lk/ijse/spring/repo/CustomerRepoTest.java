package lk.ijse.spring.repo;

import lk.ijse.spring.config.JPAConfig;
import lk.ijse.spring.entity.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Chanuka Weerakkody
 * @since : 20.1.1
 **/

@WebAppConfiguration//state test configuration class
@ContextConfiguration(classes = {JPAConfig.class})//import configurations for test context
@ExtendWith(SpringExtension.class)//Run with spring extension
class CustomerRepoTest {

    @Autowired
    CustomerRepo customerRepo;//inject the dependency of customer repo

    @Test//test method
    public void saveCustomer(){
        Customer customer1 = new Customer("C001", "Chanuka", "Horana", 500000.00);
        Customer customer2 = new Customer("C002", "Pasindu", "Panadura", 100000.00);
        Customer customer3 = new Customer("C003", "Bawantha", "Kaluthara", 450000.00);
        customerRepo.save(customer1);
        customerRepo.save(customer2);
        customerRepo.save(customer3);
    }

    @Test
    public void getAllCustomers(){
        List<Customer> all= customerRepo.findAll();
        for(Customer customer: all){
            System.out.println(customer.toString());
        }
    }

    @Test
    public void searchCustomer(){
        Optional<Customer> optional = customerRepo.findById("C001");
        boolean present = optional.isPresent();
        System.out.println(present);
        Customer customer = optional.get();
        System.out.println(customer.toString());
    }

    @Test
    public void deleteCustomer(){
        customerRepo.deleteById("C003");
    }

    @Test
    public void updateCustomer(){
        if (customerRepo.existsById("C001")) {
            Customer customer1 = new Customer("C001", "Chanuka", "Bandaragama", 500000.00);
            customerRepo.save(customer1);
        }else {
            throw new RuntimeException("No customer found!");
        }
    }
}