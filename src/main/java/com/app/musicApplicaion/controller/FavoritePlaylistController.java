package com.app.musicApplicaion.controller;

import com.app.musicApplicaion.service.FavoritePlaylistService;
import com.app.musicApplicaion.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FavoritePlaylistController {
    
    private final FavoritePlaylistService favoritePlaylistService;
    private final SongService songService;

    @Autowired
    public FavoritePlaylistController(FavoritePlaylistService favoritePlaylistService, 
                                    SongService songService) {
        this.favoritePlaylistService = favoritePlaylistService;
        this.songService = songService;
    }

    @GetMapping("/createfavoriteplaylist")
    public String showCreateFavoritePlaylistForm(Model model) {
        model.addAttribute("songs", songService.getAllSongs());
        return "createfavoriteplaylist";
    }

    @PostMapping("/createfavoriteplaylist")
    public String createPlaylist(@RequestParam String name, 
                               @RequestParam(required = false) List<Long> songs) {
        favoritePlaylistService.createPlaylist(name, songs);
        return "redirect:/customer";
    }

    @GetMapping("/viewfavoriteplaylist")
    public String viewFavoritePlaylists(Model model) {
        model.addAttribute("favoritePlaylists", favoritePlaylistService.getAllPlaylists());
        return "viewfavoriteplaylist";
    }

    @PostMapping("/editfavorite/{id}")
    public String editFavoritePlaylist(@PathVariable Long id, @RequestParam String name) {
        favoritePlaylistService.updatePlaylistName(id, name);
        return "redirect:/viewfavoriteplaylist";
    }

    @PostMapping("/removeFavoriteSong/{playlistId}/{songId}")
    public String removeSongFromFavoritePlaylist(@PathVariable Long playlistId, 
                                               @PathVariable Long songId) {
        favoritePlaylistService.removeSongFromPlaylist(playlistId, songId);
        return "redirect:/viewfavoriteplaylist";
    }

    @PostMapping("/addSongToFavorite/{playlistId}/{songId}")
    public String addSongToFavoritePlaylist(@PathVariable Long playlistId, 
                                          @PathVariable Long songId) {
        favoritePlaylistService.addSongToPlaylist(playlistId, songId);
        return "redirect:/viewfavoriteplaylist";
    }

    @PostMapping("/deleteFavoritePlaylist/{id}")
    public String deleteFavoritePlaylist(@PathVariable Long id) {
        favoritePlaylistService.deletePlaylist(id);
        return "redirect:/viewfavoriteplaylist";
    }
}