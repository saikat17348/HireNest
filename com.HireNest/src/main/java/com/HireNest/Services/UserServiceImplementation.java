package com.HireNest.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HireNest.Entities.User;
import com.HireNest.Repositories.UserRepository;

@Service
public class UserServiceImplementation implements UserService{

	@Autowired
	UserRepository repo;
	
	@Override
	public boolean addUser(User user) 
	{
		User dbobject = repo.findByEmail(user.getEmail());
		
		        if(dbobject == null)
				{
		        	repo.save(user);
		        	return true;
				}
		        return false;
	}

	@Override
	public User checkEmailAndPassword(String email, String password) {
		
		User user = repo.findByEmail(email);
		if(user != null && password.equals(user.getPassword()))
		{
			return user;
		}
		else
		{
			return null;
		}
		
	}

	@Override
	public User getUserById(Long id) {
		
		 User userobject = repo.findByid(id);
		 return userobject;
		
	}

}
