package com.HireNest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HireNest.Entities.UserProfile;

public interface UserProfileRepository extends JpaRepository <UserProfile, Long>
{	
    boolean existsByEmail(String email); 
}
