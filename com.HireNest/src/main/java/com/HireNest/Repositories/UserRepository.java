package com.HireNest.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.HireNest.Entities.User;

public interface UserRepository extends JpaRepository<User, Long> 
{
	User findByEmail(String email);
	
	User findByid(Long id);

}
