package com.kat.eventplanner.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kat.eventplanner.models.User;
import com.kat.eventplanner.services.UserService;
import com.kat.eventplanner.validator.UserValidator;

@Controller
public class UserController {
    private final UserService userService;
    
    
    private final UserValidator userValidator;
    
    
    public UserController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }
    
    @RequestMapping("/registration")
    public String registerForm(@ModelAttribute("user") User user) {
        return "registrationPage.jsp";
    }
    @RequestMapping("/login")
    public String login() {
        return "loginPage.jsp";
    }
    
    @RequestMapping(value="/registration", method=RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
        userValidator.validate(user, result);
        if(result.hasErrors()) {
            return "registrationPage.jsp";
        }
        User u = userService.registerUser(user);
        session.setAttribute("userId", u.getId());
        return "redirect:/home";
    }
    
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
        // if the user is authenticated, save their user id in session
        // else, add error messages and return the login page
    	if (userService.authenticateUser(email, password)) {
    		User user = userService.findByEmail(email);
    		session.setAttribute("userId", user.getId());
    		return "redirect:/home";
    	} else {
    		model.addAttribute("error", "Invalid credentials");
    		return "loginPage.jsp";
    	}
    	
    }
    
    @RequestMapping("/home")
    public String home(HttpSession session, Model model) {
    	Long userId = (Long) session.getAttribute("userId");
    	User user = userService.findUserById(userId);
    	model.addAttribute("user", user);
    	
        return "homePage.jsp";
    }
    
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        // invalidate session
        // redirect to login page
    	session.invalidate();
    	return "redirect:/login";
    }
}

