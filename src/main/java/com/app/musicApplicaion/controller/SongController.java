package com.app.musicApplicaion.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.app.musicApplicaion.entity.Song;
import com.app.musicApplicaion.service.SongService;

@Controller
public class SongController {

    @Autowired
    private SongService songService;
   
    @PostMapping("/addSongs")
    public String addSong(@ModelAttribute Song song) {
    	boolean songStatus=songService.titleExists(song.getTitle());
    	if(songStatus==false)
    	{
        songService.addSongs(song);
        System.out.println("Song added");
        return "admin";    
    } else {
        System.out.println("Song already exists");
        
        return "error"; 
    }
    }
   
    @GetMapping("/displaysongs")
    public String viewSongs(Model model)
    {
    	List<Song> songList=songService.getAllSongs();
    	model.addAttribute("songs" ,songList);
    	return "displaysongs";
    }
    @PostMapping("/songs/{id}")
    public String deleteSong(@PathVariable Long id) {
        songService.deleteSong(id);
        return "redirect:/displaysongs";
    }
    @GetMapping("/customersongs")
    public String viewCustomerSongs(Model model) {
        List<Song> songList = songService.getAllSongs();  // Fetch the list of songs for the customer
        model.addAttribute("songs", songList);  // Add the list of songs to the model
        return "customer-songs";  // Return the name of the Thymeleaf template
    }
    
}
