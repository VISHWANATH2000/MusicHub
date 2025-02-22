package com.app.musicApplicaion.serviceimplementation;

import com.app.musicApplicaion.entity.FavoritePlaylist;
import com.app.musicApplicaion.entity.Song;
import com.app.musicApplicaion.repository.FavoritePlaylistRepository;
import com.app.musicApplicaion.repository.SongRepository;
import com.app.musicApplicaion.service.FavoritePlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FavoritePlaylistServiceImpl implements FavoritePlaylistService {
    
    @Autowired
    private FavoritePlaylistRepository favoritePlaylistRepository;
    
    @Autowired
    private SongRepository songRepository;

    @Override
    public FavoritePlaylist createPlaylist(String name, List<Long> songIds) {
        FavoritePlaylist playlist = new FavoritePlaylist();
        playlist.setName(name);
        
        if (songIds != null && !songIds.isEmpty()) {
            List<Song> songs = songRepository.findAllById(songIds);
            playlist.setSongs(songs);
        }
        
        return favoritePlaylistRepository.save(playlist);
    }

    @Override
    public List<FavoritePlaylist> getAllPlaylists() {
        return favoritePlaylistRepository.findAll();
    }

    @Override
    public void updatePlaylistName(Long id, String name) {
        FavoritePlaylist playlist = getPlaylistById(id);
        playlist.setName(name);
        favoritePlaylistRepository.save(playlist);
    }

    @Override
    public void removeSongFromPlaylist(Long playlistId, Long songId) {
        FavoritePlaylist playlist = getPlaylistById(playlistId);
        playlist.getSongs().removeIf(song -> song.getId().equals(songId));
        favoritePlaylistRepository.save(playlist);
    }

    @Override
    public FavoritePlaylist getPlaylistById(Long id) {
        return favoritePlaylistRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Favorite Playlist not found with id: " + id));
    }

    @Override
    public void deletePlaylist(Long id) {
        FavoritePlaylist playlist = getPlaylistById(id);
        favoritePlaylistRepository.delete(playlist);
    }

    @Override
    public void addSongToPlaylist(Long playlistId, Long songId) {
        FavoritePlaylist playlist = getPlaylistById(playlistId);
        Song song = songRepository.findById(songId)
            .orElseThrow(() -> new RuntimeException("Song not found with id: " + songId));
            
        if (!playlist.getSongs().contains(song)) {
            playlist.getSongs().add(song);
            favoritePlaylistRepository.save(playlist);
        }
    }
}