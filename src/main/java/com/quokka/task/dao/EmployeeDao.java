package com.quokka.task.dao;

import java.util.Date;
import java.util.List;

import com.quokka.task.co.EmployeeCo;
import com.quokka.task.entity.Employee;
import com.quokka.task.entity.Pagination;



public interface EmployeeDao {
	public Employee save(EmployeeCo employeeCo);
	public Employee findByMobile(String mobile);
	//public Employee findByEmpId(String empid);
	//public Employee findByEmpId(EmployeeCo employeeCo);
	public Employee get(String emp_Id);
	public Employee get(int emp_Id);
	public void softdelete(int emp_Id);
	public List<Employee> ShowAll();
    public void sort(List<Employee> employees, String column);
    public List<Employee> findbyColumn(String emp_Id);
    
   



}
