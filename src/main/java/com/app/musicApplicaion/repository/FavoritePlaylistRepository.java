package com.app.musicApplicaion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.musicApplicaion.entity.FavoritePlaylist;

@Repository
public interface FavoritePlaylistRepository extends JpaRepository<FavoritePlaylist, Long> {
    // Add custom queries if needed
}