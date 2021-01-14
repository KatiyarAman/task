package com.quokka.task.co;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.lang.NonNull;

import com.quokka.task.entity.Attendence;
import com.quokka.task.entity.Salary;

public class EmployeeUpdateCo {
	@NotBlank
    @NonNull
    private String emp_name;
	
	private String emp_department;
	
	private List<Attendence> attendence;
	
	private List<Salary> salary;
	
	private String mobile;
	
	private int e_Id;

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

	public String getMobile() {
		return mobile;
	}

	public int getE_Id() {
		return e_Id;
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

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setE_Id(int e_Id) {
		this.e_Id = e_Id;
	}
	
}
