package com.quokka.task.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.quokka.task.co.EmployeeCo;
import com.quokka.task.dao.EmployeeDao;
import com.quokka.task.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>,EmployeeDao {

	
	
}
