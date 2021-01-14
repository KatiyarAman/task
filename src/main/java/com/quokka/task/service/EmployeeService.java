package com.quokka.task.service;

import java.util.List;

import com.quokka.task.co.EmployeeCo;
import com.quokka.task.co.EmployeeUpdateCo;
import com.quokka.task.dto.EmployeeDto;
import com.quokka.task.dto.EmployeeReportDto;
import com.quokka.task.entity.Employee;

public interface EmployeeService {
	public EmployeeDto save(EmployeeCo employeeCo);
    public EmployeeDto findByMobile(String mobile);
    
    public EmployeeDto update(EmployeeUpdateCo employeeUpdateCo,String em_Id);
    public EmployeeDto get(String em_Id);
    public EmployeeDto get(int emp_Id);
    public List<EmployeeDto> ShowAll();
    public void softDelete(int emp_Id);
    //public EmployeeDto findByEmpId(EmployeeCo employeeCo);
	public boolean isExist(String empid);
	public List<Employee> findbyColumn(String emp_Id);
	
}
