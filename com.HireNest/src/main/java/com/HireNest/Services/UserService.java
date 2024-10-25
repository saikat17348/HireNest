package com.HireNest.Services;

import com.HireNest.Entities.User;

public interface UserService {
	
	boolean addUser(User user);

	User checkEmailAndPassword(String email, String password);
	
	User getUserById(Long id);

}
