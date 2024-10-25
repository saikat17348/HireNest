package com.HireNest.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HireNest.Entities.Jobs;

public interface JobsRepository extends JpaRepository<Jobs, Long> {
	
	List<Jobs> searchByTitle(String title);

	List<Jobs> searchByLocation(String location);

	List<Jobs> searchByExperienceLevel(String experienceLevel);

//	List<Jobs> searchByCompanyName(String company);
	
    Jobs getById(Long Id);

	List<Jobs> findByEmployerDetails_Id(Long empId);
	
	Optional<Jobs> findById(Long Id);
	

}
