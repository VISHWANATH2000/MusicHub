package com.app.musicApplicaion.controller;

import com.app.musicApplicaion.entity.Song;
import com.app.musicApplicaion.entity.Users;
import com.app.musicApplicaion.service.SongService;
import com.app.musicApplicaion.service.UsersService;


import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsersController {
    
    @Autowired
    private UsersService service;
    @Autowired
    private SongService songService;
    @PostMapping("/signup")
    public String addUser(@ModelAttribute Users user) {
       boolean userStatus=service.emailExists(user.getEmail());
        
        if (userStatus==false) {
        	service.addUsers(user);
            //System.out.println("User added");
            return "login"; 
        } else {
           // System.out.println("User already exists");
            
            return "error"; 
        }
    }

    @PostMapping("/login")
	public String validateUser(@RequestParam String email,
			@RequestParam String password, HttpSession session)
	{
		//invoking validateUser() in service
		if(service.validateUser(email, password) == true)
		{
			
			session.setAttribute("email", email);
			//checking whether the user is admin or customer
			if(service.getRole(email).equals("admin"))
			{
				return "admin";
			}
			else
			{
				return "customer";
			}
		}
		else
		{
			return "login";
		}
	}
	
    @GetMapping("/exploreSongs")
    public String exploreSongs(HttpSession session, Model model) {
        // Check if email exists in the session
        String email = (String) session.getAttribute("email");

        // Fetch user based on email
        Users user = service.getUser(email);

        // Check if the user is null
        if (user == null) {
            // Redirect to login page if the user is not found
            return "redirect:/login";
        }

        // Check if the user is not premium
        if (!user.isPremium()) {
            // Redirect to payment page if the user is not premium
            return "payment";
        }

        // If the user is premium, fetch and display the songs

    	List<Song> songList=songService.getAllSongs();
    	model.addAttribute("songs" ,songList);
        return "customersongs";
    }

}
