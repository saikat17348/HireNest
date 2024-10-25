package com.HireNest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HireNest.Entities.EmployerDetails;

public interface EmployeeRepository extends JpaRepository<EmployerDetails, Long> {


	EmployerDetails findEmpById(Long empId);

	EmployerDetails getByEmail(String email);

	EmployerDetails findByEmail(String email);

//	EmployerDetails findByEmpEmail(String email);

//	EmployerDetails findByEmail(String email);
}
