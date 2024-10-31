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
@RequestMapping(value = "/customers")
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
        model.addAttribute("title", "addCustomer");
        model.addAttribute(new Customer());

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
        return "redirect:/customers/profile";

    }

}



    @GetMapping("/profile")
    public String displayCustomerProfile(Model model){
        model.addAttribute("title", "customerProfile");
        model.addAttribute("customers", customerRepository.findAll()); // List all customers
        return "customers/profile";
    }

    

//    @GetMapping("/edit/{id}")
//    public String displayCustomerUpdateProfile(@PathVariable int id, Model model) {
//
//        List<Customer> optCustomer = (List<Customer>) customerRepository.findById();
//        model.addAttribute("title", "editProfile");
//        model.addAttribute(new Customer());
//
//        model.addAttribute("customer", optCustomer);
//        return "customers/update-customer";
//    }



//    @GetMapping("/edit/{id}/")
//    public String displayCustomerForm(Model model, @PathVariable int id) {
//
////        if (userId !=null) {
//        Optional<Customer> optCustomer = customerRepository.findById(id);
//        if (optCustomer.isPresent()) {
//            Customer customer = optCustomer.get();
//            customerRepository.save(customer);
//
//            model.addAttribute("customer", customer);
//
//            return "customers/profile";
//           // return "customers/update-customer";
//
//
//
//        } else {
//
//            model.addAttribute("customers", customerRepository.findAll());
//
//            return "redirect:/customers/list";
//
//        }
//
//    }






    @GetMapping("/delete")// works for individuals case
    public String processDeleteCustomerForm(@RequestParam (required = false) int id){

            customerRepository.deleteById(id);

/*
            works both ways
            Customer customer = customerRepository.findById(id).get();
            customerRepository.delete(new Customer());
*/

        return  "redirect:/customers/list";
    }


//    @PostMapping("/delete") // works for checkbox
//    public String processDeleteCustomerForm(@RequestParam(required = false) int[] customerId) {
//        for (int id : customerId) {
//            customerRepository.deleteById(id);
//        }
//        return "redirect:/";
//    }




    @GetMapping("/edit/{id}")
    public String showCustomerUpdateForm(@PathVariable int id, Model model) {
        Optional<Customer> optCustomer =  customerRepository.findById(id);
        if(optCustomer.isPresent()) {
//        .orElseThrow(() - > new IllegalArgumentException("Invalid Task Id:" + id));
            Customer customer = optCustomer.get();
//            Customer customer = customerRepository.findById(id).get();
            customerRepository.save(customer);
            model.addAttribute("title", "EditCustomer");

            model.addAttribute("customer",customer);
        }

//        model.addAttribute("customer", customerRepository.findById(id));
        return "customers/update-customer";
    }



    @PostMapping("/edit/{id}")
    public String processUpdateCustomerForm(@RequestParam (required = false) Integer id, @ModelAttribute @Valid Customer newCustomer, Errors errors, Model model){

        if(errors.hasErrors()){
            model.addAttribute("title", "EditCustomer");
            return "customers/update-customer";
        }
        else{

            model.addAttribute("title", "customer-list");


            Optional<Customer> customer = Optional.of(customerRepository.findById(id).get());
//            customerRepository.findById(id).get();
           customerRepository.save(newCustomer);

//           customerRepository.save(customer);
            model.addAttribute("customer", customerRepository.findAll());

            return "redirect:/customers/profile";

        }

    }

}



