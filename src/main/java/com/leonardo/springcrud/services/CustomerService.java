package com.leonardo.springcrud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonardo.springcrud.entities.Customer;
import com.leonardo.springcrud.repositories.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findAll() {
        return customerRepository.findAllByOrderByLastName();   
    }

    public Customer findById(int id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElse(null);
    }

    public void saveOrUpdate(Customer customer) {
        if (customer.getId() == null) {
            customerRepository.save(customer);
        } else {
            Customer tempCustomer = findById(customer.getId());
            updateData(customer, tempCustomer);
            customerRepository.save(tempCustomer);
        }
    }

    public void delete(int id) {
        Customer customer = findById(id);
        customerRepository.delete(customer);
    }

    private void updateData(Customer customer, Customer tempCustomer) {
        tempCustomer.setFirstName(customer.getFirstName());
        tempCustomer.setLastName(customer.getLastName());
        tempCustomer.setEmail(customer.getEmail());
    }
}

