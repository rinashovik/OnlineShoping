package com.simple_world.eShop.controllers;

import com.simple_world.eShop.data.CustomerRepository;
import com.simple_world.eShop.model.Customer;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "/customer/")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/")
    public String helloCustomerPage(){

        return "customer/index";

    }
//    @GetMapping("/list")
//    public String displayCustomerList(Model model){
//        model.addAttribute("title", "customer-list");
//        model.addAttribute("customers", customerRepository.findAll());
//        return "customer/customer-list";
//    }
//
@GetMapping("/list")
public String displayCustomerList(Model model){
    model.addAttribute("title", "customerlist");
    model.addAttribute("customers", customerRepository.findAll());
    return "customer/customer-list";
}



    @GetMapping("/add")
    public String displayCreateCustomerForm(Model model){
        model.addAttribute(new Customer());
        model.addAttribute("title", "addCustomer");
        return "customer/add-customer";

    }

@PostMapping("/add")
    public String processCreateCustomerForm(@ModelAttribute @Valid Customer newCustomer, Errors errors, Model model){

    if(errors.hasErrors()){
        model.addAttribute("title", "addCustomer");
        return "customer/add-customer";
    }
    else{
        model.addAttribute("title", "customerlist");
        customerRepository.save(newCustomer);
        return "redirect:/customer/profile";

    }

}



    @GetMapping("/profile")
    public String displayCustomerProfile(Model model){
        model.addAttribute("title", "customerProfile");
        model.addAttribute("customers", customerRepository.findAll());
        return "customer/profile";
    }

    @GetMapping("/edit")
    public String displayCustomerUpdateProfile(Model model){
        model.addAttribute("title", "editProfile");
        model.addAttribute(new Customer());
        return "customer/update-customer";
    }




    @GetMapping("/delete")
    public String processDeleteCustomerForm(){

        return "customer/delete-customer";
}


}



