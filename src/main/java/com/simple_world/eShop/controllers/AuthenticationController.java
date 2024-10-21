package com.simple_world.eShop.controllers;

import com.simple_world.eShop.data.UserRepository;
import com.simple_world.eShop.model.User;
import com.simple_world.eShop.model.dto.LoginFormDTO;
import com.simple_world.eShop.model.dto.RegisterFormDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class AuthenticationController {


    @Autowired
    private UserRepository userRepository;
    private static final String userSessionKey = "user";

    public User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }

        Optional<User> user = userRepository.findById(userId);

//        return user.orElse(null);

        if (user.isEmpty()) {
            return null;
        }

        return user.get();
    }

    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getId());
    }



    @GetMapping("/register")
    public String displayRegistrationForm(Model model) {
        model.addAttribute(new RegisterFormDTO());
        model.addAttribute("title", "Register");
        return "register-form";
    }

    @PostMapping("/register")
    public String processRegistrationForm(@ModelAttribute @Valid RegisterFormDTO registerFormDTO,
                                          Errors errors, HttpServletRequest request,
                                          Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Register");
            return "register-form";
        }

        User existingUser = userRepository.findByUsername(registerFormDTO.getUsername());

        if (existingUser != null) {
            errors.rejectValue("username", "username.alreadyexists", "A user with that username already exists");
            model.addAttribute("title", "Register");
//return ResponseEntity.badRequest().body(Collections.singletonMap("error", "User with the given username already exists"));

            return "register-form";
        }

        String password = registerFormDTO.getPassword();
        String verifyPassword = registerFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            model.addAttribute("title", "Register");
            return "register-form";
        }

        User newUser = new User(registerFormDTO.getUsername(), registerFormDTO.getPassword());
        userRepository.save(newUser);
        setUserInSession(request.getSession(), newUser);
        return "redirect:/";

        //return "redirect:/login";
        // return "redirect:/new-user";

    }
    @GetMapping("/new-user")
    public String displayNewUser(Model model) {
        // model.addAttribute(new LoginFormDTO());
        model.addAttribute("title", "NewUser");
        return "new-user";
    }

    @GetMapping("/login")
    public String displayLoginForm(Model model) {
        model.addAttribute(new LoginFormDTO());
        model.addAttribute("title", "Log In");
        return "login-form";
    }


    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO,
                                   Errors errors, HttpServletRequest request,
                                   Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Log In");
            return "login-form";
        }

        User theUser = userRepository.findByUsername(loginFormDTO.getUsername());

        if (theUser == null) {
            errors.rejectValue("username", "user.invalid", "The given username does not exist");
            model.addAttribute("title", "Log In");
            return "login-form";
        }

        String password = loginFormDTO.getPassword();

        if (!theUser.isMatchingPassword(password)) {
            errors.rejectValue("password", "password.invalid", "Invalid password");
            model.addAttribute("title", "Log In");
            return "login-form";
        }

        setUserInSession(request.getSession(), theUser);

        return "redirect:/index";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/login";
    }


    @GetMapping("/help-reset")
    public String reSetPassword(Model model) {
        model.addAttribute(new LoginFormDTO());
        model.addAttribute("title", "Password-reset");
        return "Password-reset";
    }


    @GetMapping("/reset")
    public String displayResetForm(Model model) {
        model.addAttribute(new RegisterFormDTO());
        model.addAttribute("title", "password-reset");
        return "reset-password";
    }
    @PostMapping("/reset")
    public String processResetPasswordForm(@ModelAttribute @Valid RegisterFormDTO resetFormDTO,
                                           Errors errors, HttpServletRequest request,
                                           Model model) {
        model.addAttribute(new RegisterFormDTO());

        //RegisterFormDTO resetFormDTO = null;
        String password1 = resetFormDTO.getPassword();
        String verifyPassword1 = resetFormDTO.getVerifyPassword();
        if (!password1.equals(verifyPassword1)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            model.addAttribute("title", "Password");
            return "reset-password";
        }

        User newUser1 = new User(resetFormDTO.getUsername(), resetFormDTO.getPassword());
        userRepository.save(newUser1);
        setUserInSession(request.getSession(), newUser1);
        return "redirect:/";
    }

//
//
//        return "password-reset";
//    }

}
