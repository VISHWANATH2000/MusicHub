package com.app.musicApplicaion.entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
	@Table(name = "favorite_playlists")
	public class FavoritePlaylist {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    private String name;
	    
	    @ManyToMany
	    @JoinTable(
	        name = "favorite_playlist_songs",
	        joinColumns = @JoinColumn(name = "playlist_id"),
	        inverseJoinColumns = @JoinColumn(name = "song_id")
	    )
	    private List<Song> songs = new ArrayList<>();

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<Song> getSongs() {
			return songs;
		}

		public void setSongs(List<Song> songs) {
			this.songs = songs;
		}

		public FavoritePlaylist(Long id, String name, List<Song> songs) {
			super();
			this.id = id;
			this.name = name;
			this.songs = songs;
		}

		@Override
		public String toString() {
			return "FavoritePlaylist [id=" + id + ", name=" + name + ", songs=" + songs + "]";
		}

		public FavoritePlaylist() {
			super();
			// TODO Auto-generated constructor stub
		}

	}

