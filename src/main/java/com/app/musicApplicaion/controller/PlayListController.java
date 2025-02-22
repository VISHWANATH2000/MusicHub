package com.app.musicApplicaion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.musicApplicaion.entity.PlayList;
import com.app.musicApplicaion.entity.Song;
import com.app.musicApplicaion.service.PlayListService;
import com.app.musicApplicaion.service.SongService;
import org.springframework.web.servlet.view.RedirectView;
@Controller
public class PlayListController {

    @Autowired
    PlayListService playlistservice;

    @Autowired
    SongService songService;

    @GetMapping("/createplaylist")
    public String createPlayList(Model model) {
        List<Song> songList = songService.getAllSongs();
        model.addAttribute("songs", songList);
        return "createplaylist"; // Thymeleaf template name
    }

    @PostMapping("/addplaylist")
    public String addPlayList(@ModelAttribute PlayList playlist) {
        PlayList existingPlaylist = playlistservice.findByName(playlist.getName());

        if (existingPlaylist != null) {
            // Add songs to the existing playlist
            for (Song song : playlist.getSongs()) {
                if (!existingPlaylist.getSongs().contains(song)) {
                    existingPlaylist.getSongs().add(song);
                    song.getPlaylist().add(existingPlaylist); // Assuming a List<PlayList> in Song
                }
            }
            playlistservice.updatePlaylist(existingPlaylist);
        } else {
            // Create a new playlist
            playlistservice.addPlaylist(playlist);
            for (Song song : playlist.getSongs()) {
                song.getPlaylist().add(playlist); // Assuming a List<PlayList> in Song
                songService.updateSong(song);
            }
        }

        return "redirect:/viewplaylist"; // Redirect to view the updated playlist
    }

    @GetMapping("/viewplaylist")
    public String viewPlaylists(Model model) {
        List<PlayList> playlist = playlistservice.getAllPlaylists();
        model.addAttribute("playList", playlist);
        return "viewplaylist"; // Thymeleaf template name
    }

    @PostMapping("/edit/{id}")
    public String editPlaylistName(@PathVariable Long id, @RequestParam("name") String newName) {
        playlistservice.updatePlaylistName(id, newName);
        return "redirect:/viewplaylist";
    }


    @PostMapping("/removeSong/{playlistId}/{songId}")
    public RedirectView removeSong(@PathVariable Long playlistId, @PathVariable Long songId) {
        // Remove the song from the playlist without deleting the song itself
        playlistservice.removeSongFromPlaylist(playlistId, songId);

        // Redirect back to the playlist page after removing the song
        return new RedirectView("/viewplaylist");
    }


    @GetMapping("/playlists/{id}")
    public String getPlaylist(@PathVariable Long id, Model model) {
        PlayList playlist = playlistservice.findById(id);
        model.addAttribute("playlist", playlist);
        return "playlist"; // Assuming "playlist" is the name of your Thymeleaf template
    }
    @GetMapping("/view-playlists-customer")
    public String viewPlaylistsCustomer(Model model) {
        List<PlayList> playlists = playlistservice.getAllPlaylists();
        model.addAttribute("playList", playlists);
        return "view-playlists-customer"; // Thymeleaf template for customer view
    }
    @PostMapping("/deletePlaylist/{id}")
    public String deleteFavoritePlaylist(@PathVariable Long id) {
        playlistservice.deletePlaylist(id);
        return "redirect:/viewplaylist";
    }

}
