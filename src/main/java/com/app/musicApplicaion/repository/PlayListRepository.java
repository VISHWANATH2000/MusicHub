package com.app.musicApplicaion.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.musicApplicaion.entity.PlayList;

public interface PlayListRepository extends JpaRepository<PlayList, Long> {

	List<PlayList> findByName(String name);
    // Additional query methods (if needed) can be defined here
}