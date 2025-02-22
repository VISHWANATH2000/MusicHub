package com.app.musicApplicaion.serviceimplementation;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.musicApplicaion.entity.FavoritePlaylist;
import com.app.musicApplicaion.entity.PlayList;
import com.app.musicApplicaion.entity.Song;
import com.app.musicApplicaion.repository.PlayListRepository;
import com.app.musicApplicaion.service.PlayListService;

@Service
public class PlayListServiceImpl implements PlayListService {

    @Autowired
    private PlayListRepository playlistRepository;

    @Override
    public PlayList addPlaylist(PlayList playlist) {
        return playlistRepository.save(playlist);
    }

    @Override
    public List<PlayList> getAllPlaylists() {
        return playlistRepository.findAll();
    }

    @Override
    public void removeSongFromPlaylist(Long playlistId, Long songId) {
        // Find the playlist by ID (not using Optional)
        PlayList playlist = playlistRepository.findById(playlistId)
            .orElseThrow(() -> new RuntimeException("Playlist not found"));

        // Find the song by ID and remove it from the playlist's song list
        List<Song> updatedSongs = playlist.getSongs().stream()
            .filter(song -> !song.getId().equals(songId)) // Keep only songs that are not the one to be removed
            .collect(Collectors.toList());

        // Set the updated song list
        playlist.setSongs(updatedSongs);
        
        // Save the playlist with the updated song list
        playlistRepository.save(playlist);
    }

    @Override
    public void updatePlaylistName(Long playlistId, String newName) {
        PlayList playlist = playlistRepository.findById(playlistId).orElseThrow(() -> new RuntimeException("Playlist not found"));
        playlist.setName(newName);
        playlistRepository.save(playlist);
    }

    @Override
    public PlayList findByName(String name) {
        List<PlayList> playlists = playlistRepository.findByName(name);
        if (playlists.isEmpty()) {
            return null; // Or throw an exception if you prefer
        }
        // Return the first one or handle as needed
        return playlists.get(0); // Adjust based on your logic
    }

    @Override
    public PlayList updatePlaylist(PlayList playlist) {  // Implement the method
        return playlistRepository.save(playlist);
    }

    @Override
    public PlayList findById(Long id) {
        PlayList playlist = playlistRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Playlist not found"));
        return playlist;
    }


    @Override
    public void deletePlaylist(Long id) {
        try {
        	  PlayList playlist = getPlaylistById(id);
            
            // Clear the songs collection first
            playlist.getSongs().clear();
            playlistRepository.save(playlist); // Save to update the join table
            
            // Now delete the playlist
            playlistRepository.delete(playlist);
            
        } catch (Exception e) {
            throw new RuntimeException("Error deleting playlist: " + e.getMessage());
        }
    }
    @Override
    public PlayList getPlaylistById(Long id) {
        return playlistRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Favorite Playlist not found with id: " + id));
    }
       
    }

