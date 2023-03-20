package com.leonardo.springcrud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.leonardo.springcrud.entities.Customer;
import com.leonardo.springcrud.services.CustomerService;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    
    @GetMapping("/")
    public ModelAndView index() {
        List<Customer> customers = customerService.findAll();

        ModelAndView mv = generateModelAndView("index");
        mv.addObject("customers", customers);
        return mv; 
    }

    @GetMapping("/showFormForAdd")
    public ModelAndView showFormForAdd() {
        Customer customer = new Customer();
        
        ModelAndView mv = generateModelAndView("customer-form");
        mv.addObject("customer", customer);
        return mv;
    }

    @GetMapping("/showFormForUpdate")
    public ModelAndView showFormForUpdate(@RequestParam("id") int customerId) {
        Customer customer = customerService.findById(customerId);

        ModelAndView mv = generateModelAndView("customer-form");
        mv.addObject("customer", customer);
        return mv;
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.saveOrUpdate(customer);
        return "redirect:/";
    }

    @GetMapping("/deleteCostumer")
    public String deleteCostumer(@RequestParam("id") int customerId) {
        customerService.delete(customerId);
        return "redirect:/";
    }

    private ModelAndView generateModelAndView(String viewName) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(viewName);
        return mv;
    }
}
