package com.HireNest.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HireNest.Entities.Applications;

import jakarta.transaction.Transactional;

public interface ApplicationsRepository extends JpaRepository<Applications, Long>
{

	List<Applications> findByEmployerDetails_Id(Long id);

	List<Applications> findByJobs_Id(Long id);

	Applications findApplicationsById(Long id);
    
	//@Transactional
  // void deleteByJobsId(Long jobid);

	void deleteByJobs_Id(Long id);

}
