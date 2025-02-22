package com.app.musicApplicaion.service;

import java.util.List;

import com.app.musicApplicaion.entity.Song;

public interface SongService {
   public  String addSongs(Song song);
    public List<Song> getAllSongs();
    public  boolean titleExists(String title);
    public boolean findByTitle(String title);
	public void updateSong(Song song);
	public void deleteSong(Long id);
 
	
}
