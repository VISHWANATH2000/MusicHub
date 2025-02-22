package com.app.musicApplicaion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.musicApplicaion.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
	public Users findByEmail(String email);
	    boolean existsByEmail(String email);
}
