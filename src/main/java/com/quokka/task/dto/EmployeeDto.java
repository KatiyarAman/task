package com.quokka.task.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.quokka.task.co.EmployeeCo;
import com.quokka.task.entity.Attendence;
import com.quokka.task.entity.Employee;
import com.quokka.task.entity.Salary;

public class EmployeeDto {
	@NotBlank
    @NonNull
    private String emp_name;
	
	private String emp_department;
	
	private List<Attendence> attendence;
	private int emp_Id;
	private String mobile;
	private List<Salary> salary;
	 @JsonProperty("registrationDate")
	    private Date dateCreated;
	
	 
	 public EmployeeDto() {
		super();
	}
	public EmployeeDto(Employee ed) {
		// TODO Auto-generated constructor stub
		this.emp_name=ed.getEmp_name();
		this.emp_department=ed.getEmp_department();
		//this.attendence=ed.getAttendence();
		//this.salary=ed.getSalary();
		this.emp_Id=ed.getEmp_Id();
		this.mobile=ed.getMobile();
		
	}
	
	public String getEmp_name() {
		return emp_name;
	}
	public String getEmp_department() {
		return emp_department;
	}
	public List<Attendence> getAttendence() {
		return attendence;
	}
	public List<Salary> getSalary() {
		return salary;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public void setEmp_department(String emp_department) {
		this.emp_department = emp_department;
	}
	public void setAttendence(List<Attendence> attendence) {
		this.attendence = attendence;
	}
	public void setSalary(List<Salary> salary) {
		this.salary = salary;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public int getEmp_Id() {
		return emp_Id;
	}
	public void setEmp_Id(int emp_Id) {
		this.emp_Id = emp_Id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
