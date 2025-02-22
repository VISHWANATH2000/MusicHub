package com.app.musicApplicaion.repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


import com.app.musicApplicaion.entity.Song;

public interface SongRepository extends JpaRepository<Song, Long> {
	Optional<Song> findByTitle(String title);
    boolean existsByTitle(String title);
	
   
    }