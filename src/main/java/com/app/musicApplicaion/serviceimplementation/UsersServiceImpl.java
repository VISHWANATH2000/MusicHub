package com.app.musicApplicaion.serviceimplementation;
import com.app.musicApplicaion.entity.Users;
import com.app.musicApplicaion.repository.UsersRepository;
import com.app.musicApplicaion.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository userRepository;

    @Override
    public String addUsers(Users user) {
        if (emailExists(user.getEmail())) {
            return "User already exists with this email";
        }
        userRepository.save(user);
        return "User added successfully";
    }

    @Override
    public boolean validateUser(String email, String password) {
    	Users user = userRepository.findByEmail(email);
        if (user == null) {
            return false;
        }
		String db_password = user.getPassword();
		if(db_password.equals(password))
		{
			return true;
		}
		else
		{
			return false;
		}
    }

    @Override
    public Users findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean emailExists(String email) {

		if(userRepository.findByEmail(email) == null) 
		{
			return false;
		}
		else
		{
			return true;
		}
    }

	@Override
	public Users getUser(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void updateUser(Users user) {
		userRepository.save(user);
		
	}

	@Override
	public String getRole(String email) {
		return (userRepository.findByEmail(email).getRole());
	}
}
