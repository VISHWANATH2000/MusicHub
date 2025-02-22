package com.app.musicApplicaion.service;

import com.app.musicApplicaion.entity.Users;

public interface UsersService {
    public String addUsers(Users user);
    public  boolean emailExists(String email);
    public  boolean validateUser(String email, String password);
	public Users findByEmail(String email);
	public Users getUser(String email);

	public void updateUser(Users user);
	public String getRole(String email);
}
