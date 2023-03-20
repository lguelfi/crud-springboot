package com.leonardo.springcrud.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonardo.springcrud.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    
    List<Customer> findAllByOrderByLastName();
}
