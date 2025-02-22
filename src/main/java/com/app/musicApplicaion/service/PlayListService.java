package com.app.musicApplicaion.service;

import java.util.List;

import com.app.musicApplicaion.entity.FavoritePlaylist;
import com.app.musicApplicaion.entity.PlayList;

public interface PlayListService {
    PlayList addPlaylist(PlayList playlist);
    List<PlayList> getAllPlaylists();
    void updatePlaylistName(Long id, String newName);
    void removeSongFromPlaylist(Long playlistId, Long songId);
    PlayList findByName(String name);

    PlayList updatePlaylist(PlayList playlist);
    PlayList findById(Long id);
	void deletePlaylist(Long id);
	PlayList getPlaylistById(Long id);
    
}
