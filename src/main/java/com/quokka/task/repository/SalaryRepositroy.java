package com.quokka.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quokka.task.dao.SalaryDao;
import com.quokka.task.entity.Salary;

public interface SalaryRepositroy extends JpaRepository<Salary,Integer>,SalaryDao {

}
