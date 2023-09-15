package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.entity.Customer;
import lk.ijse.spring.repo.CustomerRepo;
import lk.ijse.spring.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Chanuka Weerakkody
 * @since : 20.1.1
 **/
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    /*@Autowired
    ModelMapper mapper;*/

    @Override
    public void saveCustomer(CustomerDTO dto){
        if(!customerRepo.existsById(dto.getId())){
            //Customer entity = mapper.map(dto, Customer.class);
            //customerRepo.save(mapper.map(dto, Customer.class));
            Customer customer = new Customer(dto.getId(), dto.getName(), dto.getAddress(), dto.getSalary());
            customerRepo.save(customer);
        }else {
            throw new RuntimeException("Customer id is already exists!!!");
        }
    }

    @Override
    public void deleteCustomer(String id){
        if (customerRepo.existsById(id)) {
            customerRepo.deleteById(id);
        }else{
            throw new RuntimeException("Customer id is not found to delete!");
        }
    }

    @Override
    public void updateCustomer(CustomerDTO dto){
        if (customerRepo.existsById(dto.getId())) {
            //Customer entity = mapper.map(dto, Customer.class);
            Customer customer = new Customer(dto.getId(), dto.getName(), dto.getAddress(), dto.getSalary());
            customerRepo.save(customer);
        }else{
            throw new RuntimeException("No customer found to update!");
        }
    }

    @Override
    public CustomerDTO searchCustomer(String id){
        if (customerRepo.existsById(id)) {
            Customer customer = customerRepo.findById(id).get();
            //return mapper.map(customerRepo.findById(id).get(), CustomerDTO.class);
            return new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress(), customer.getSalary());
        }else{
            throw new RuntimeException("No customer "+id+" ..!");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomer(){
        List<Customer> all = customerRepo.findAll();
        List<CustomerDTO> arrayList=new ArrayList<>();
        for (Customer customer : all) {
            arrayList.add(new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress(), customer.getSalary()));
        }
        //return mapper.map(all,new TypeToken<CustomerDTO>(){}.getType());
        return arrayList;
    }
}
