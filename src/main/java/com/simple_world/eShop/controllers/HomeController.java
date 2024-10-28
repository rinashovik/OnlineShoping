package com.simple_world.eShop.controllers;

import com.simple_world.eShop.data.CustomerRepository;
import com.simple_world.eShop.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired private CustomerRepository customerRepository;


    @GetMapping("/")
    public String welcomeHomePage(){

        return "home";

    }

    @GetMapping("/index")
    public String WelcomeCustomerDashboardPage(){

        return "index";

    }


//    @GetMapping("/login")
//    public String loginForm(){
//
//        return "login-form";
//
//
//    }
//
    @GetMapping("/new-user")
    public String displayNewUserPage(){


        return "new-user";
    }


    @GetMapping("/contact")
    public String WelcomeCustomerContactPage(Model model){

            model.addAttribute(new Customer());
            model.addAttribute("title", "addCustomer");



        return "contact";

    }
    @GetMapping("/settings")
    public String WelcomeCustomerSettingsPage(){

        return "settings";

    }


}
