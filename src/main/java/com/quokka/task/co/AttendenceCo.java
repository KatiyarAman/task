package com.quokka.task.co;

import javax.validation.constraints.NotBlank;

import org.springframework.lang.NonNull;

import com.quokka.task.entity.Employee;

import io.swagger.annotations.ApiModel;
@ApiModel(value = "Attendence", description = "Request for : Add new Employee details for order")
public class AttendenceCo {
	@NotBlank
    @NonNull
    private String emp_checkin;
	private String emp_checkout;
	private String emp_attendence;
	private String empdate;
	private String emp_month;
	private int emp_Id;
	private Employee employeeattendence;
	public String getEmp_checkin() {
		return emp_checkin;
	}
	public String getEmp_checkout() {
		return emp_checkout;
	}
	public String getEmp_attendence() {
		return emp_attendence;
	}
	public String getEmpdate() {
		return empdate;
	}
	public void setEmp_checkin(String emp_checkin) {
		this.emp_checkin = emp_checkin;
	}
	public void setEmp_checkout(String emp_checkout) {
		this.emp_checkout = emp_checkout;
	}
	public void setEmp_attendence(String emp_attendence) {
		this.emp_attendence = emp_attendence;
	}
	public void setEmpdate(String empdate) {
		this.empdate = empdate;
	}
	
	public Employee getEmployeeattendence() {
		return employeeattendence;
	}
	public void setEmployeeattendence(Employee employeeattendence) {
		this.employeeattendence = employeeattendence;
	}
	public int getEmp_Id() {
		return emp_Id;
	}
	public void setEmp_Id(int emp_Id) {
		this.emp_Id = emp_Id;
	}
	public String getEmp_month() {
		return emp_month;
	}
	public void setEmp_month(String emp_month) {
		this.emp_month = emp_month;
	}
	
	
}
