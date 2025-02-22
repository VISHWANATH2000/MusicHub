package com.app.musicApplicaion.service;

import com.app.musicApplicaion.entity.FavoritePlaylist;
import java.util.List;

public interface FavoritePlaylistService {
    FavoritePlaylist createPlaylist(String name, List<Long> songIds);
    
    List<FavoritePlaylist> getAllPlaylists();
    
    void updatePlaylistName(Long id, String name);
    
    void removeSongFromPlaylist(Long playlistId, Long songId);
    
    FavoritePlaylist getPlaylistById(Long id);
    
    void deletePlaylist(Long id);
    
    void addSongToPlaylist(Long playlistId, Long songId);
}