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
        return "redirect:/customers/profile";

    }

}



    @GetMapping("/profile")
    public String displayCustomerProfile(Model model){
        model.addAttribute("title", "customerProfile");
        model.addAttribute("customers", customerRepository.findAll()); // List all customers
        return "customers/profile";
    }

// }

 //   @GetMapping("/edit/{id}")
//    public String displayCustomerUpdateProfile(@PathVariable int id, Model model){
//
//        List<Customer> optCustomer = (List<Customer>) customerRepository.findAll();
//        model.addAttribute("title", "editProfile");
//        model.addAttribute(new Customer());
//
//        model.addAttribute("customer", optCustomer);
//        return "customers/update-customer";
//
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
////            return "profile";
//            return "customers/update-customer";
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
//         works both ways
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
        List<Customer> optCustomer = (List<Customer>) customerRepository.findAll();
//        .orElseThrow(() - > new IllegalArgumentException("Invalid Task Id:" + id));

        Customer customer = customerRepository.findById(id).get();
        customerRepository.save(customer);


        model.addAttribute("customer", customerRepository.findById(id));
        return "update-customer";
    }

    @PostMapping("/{id}")
    public String updateCustomerForm(@ModelAttribute Customer newCustomer, @PathVariable int id, Model model){



        Optional<Customer> optCustomer = customerRepository.findById(id);
        customerRepository.existsById(id);


        return "redirect:/customers/profile/";
    }


//
//    @PostMapping("edit/{id}")
//    public String displayViewTask(@ModelAttribute Model model, @Valid Customer newCustomer, Errors errors, @RequestParam(required = false) List<Integer>  taskIds) {
//
//       if (taskIds !=null) {
//           List<Customer> selectedCustomer = (List<Customer>) customerRepository.findAllById(taskIds);
//           Job job = new Job();
//           job.setCustomer(selectedCustomer);
//       }
//            if(errors.hasErrors()){
//                System.out.println(errors.getAllErrors());
//       List<Customer> tasks = (List<Customer>) customerRepository.findAll();
//
//                return "update";
//            }
//            else {
//                customerRepository.save(newCustomer);
//                model.addAttribute("tasks", customerRepository.findAll());// passing task object's values
//
//
//                return "redirect:/";
//            }
//
//   //        }
//            return "update";
//
//        } else {
//
//              model.addAttribute("tasks", customerRepository.findAll());// Optional code
//
//            return "redirect:/";
//
//        }
//
//    }

//    @GetMapping("/edit")
//    public String DisplayUpdateForm(@RequestParam int id, @Valid Customer newCustomer,
//                                    Model model) {
//
//
////        if (result.hasErrors()) {
//            customer.setId(id);
//            return "update";
//        }
//        customerRepository.save(newTask);
//        model.addAttribute("task", customerRepository.findAll());
//        return "index";
//    }




}



