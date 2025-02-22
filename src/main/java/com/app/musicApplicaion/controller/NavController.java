package com.app.musicApplicaion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavController {
	 @GetMapping("/index")
	    public String showIndex() {
	        return "index";
	    }
	    @GetMapping("/signup")
	    public String showSignupForm() {
	        return "signup"; 
	    }
	    @GetMapping("/login")
	    public String showLoginForm() {
	        return "login"; 
	    }
	    @GetMapping("/songs")
	    public String showSongs() {
	        return "songs"; 
	    }
	    @GetMapping("/customer")
	    public String showCustomer() {
	        return "customer"; 
	    }
	    @GetMapping("/admin")
	    public String showAdmin() {
	        return "admin"; 
	    }
	    @GetMapping("/payment")
	    public String showPremium() {
	        return "payment"; 
	    }
	    @GetMapping("/contact")
	    public String showContact() {
	        return "contact"; 
	    }
	  
	  
}
