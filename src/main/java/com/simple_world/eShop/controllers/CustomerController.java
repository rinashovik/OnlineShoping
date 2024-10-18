package com.simple_world.eShop.controllers;

import com.simple_world.eShop.data.CustomerRepository;
import com.simple_world.eShop.model.Customer;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/customers/")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/")
    public String helloCustomerPage(){

        return "customers/index";

    }

@GetMapping("/list")
public String displayCustomerList(Model model){
    model.addAttribute("title", "customer-list");
    model.addAttribute("customers", customerRepository.findAll());
    return "customers/customer-list";
}



    @GetMapping("/add")
    public String displayCreateCustomerForm(Model model){
        model.addAttribute(new Customer());
        model.addAttribute("title", "addCustomer");
        return "customers/add-customer";

    }

@PostMapping("/add")
    public String processCreateCustomerForm(@ModelAttribute @Valid Customer newCustomer, Errors errors, Model model){

    if(errors.hasErrors()){
        model.addAttribute("title", "addCustomer");
        return "customers/add-customer";
    }
    else{
        model.addAttribute("title", "customer-list");
        customerRepository.save(newCustomer);
        return "redirect:/customer/profile";

    }

}



    @GetMapping("/profile")
    public String displayCustomerProfile(Model model){
        model.addAttribute("title", "customerProfile");
        model.addAttribute("customers", customerRepository.findAll()); // List all customers
        return "customers/profile";
    }

    @GetMapping("/edit/{id}")
    public String displayCustomerUpdateProfile(@PathVariable List<Integer> customerIds, Model model){

        List<Customer> optCustomer = (List<Customer>) customerRepository.findAll();
        model.addAttribute("title", "editProfile");
        model.addAttribute(new Customer());

        model.addAttribute("customer", optCustomer);
        return "customers/update-customer";
    }


    @GetMapping("/list/{customerId}/")
    public String displayCustomer(Model model, @PathVariable int customerId) {

//        if (userId !=null) {
        Optional<Customer> optCustomer = customerRepository.findById(customerId);
        if (optCustomer.isPresent()) {
            Customer customer = optCustomer.get();
            model.addAttribute("customer", customer);


            return "profile";


        } else {

            model.addAttribute("customers", customerRepository.findAll());

            return "redirect:/customers/list";

        }

    }

//    @GetMapping("/delete")
//    public String processDeleteCustomerForm(@RequestParam int id){
//
//        Customer customer = customerRepository.findById(id).get();
//        customerRepository.delete(customer);
//
//
//        return "redirect:/customers/list"; // path
//    }




//
//    @GetMapping("/delete")
//    public String renderDeleteCustomerForm(Model model){
//        model.addAttribute("title", "delete-customer");
//        model.addAttribute("customers", customerRepository.findAll());
//
//        return "customers/delete-customer";
//}
//
//

    @GetMapping("/delete")
    public String processDeleteCustomerForm(@RequestParam (required = false) int[] customerId){

        for (int id : customerId){
            customerRepository.findById(id).get();
        }
        customerRepository.delete(new Customer());
        return  "redirect:/customers/list";
    }

}



