package com.quokka.task.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.quokka.task.entity.Attendence;
import com.quokka.task.entity.Employee;
import com.quokka.task.entity.Salary;
import com.quokka.task.response.EmployeeResponse;


public class EmployeeReportDto implements EmployeeResponse{

//	private String location;

    public EmployeeReportDto(Employee it) {
		// TODO Auto-generated constructor stub
    	this.emp_name = it.getEmp_name();
    	this.emp_department = it.getEmp_department();
    	this.attendence = it.getAttendence();
    	this.emp_Id = it.getEmp_Id();
    	this.mobile = it.getMobile();
    	this.salary = it.getSalary();
    	this.dateCreated = it.getDateCreated();
    	
	}

	public EmployeeReportDto(String emp_name, String emp_department, List attendence, int emp_Id, String mobile,
		List<Salary> salary, Date dateCreated, float totalAmount, List<Attendence> attendences) {
	super();
	this.emp_name = emp_name;
	this.emp_department = emp_department;
	this.attendence = attendence;
	this.emp_Id = emp_Id;
	this.mobile = mobile;
	this.salary = salary;
	this.dateCreated = dateCreated;
	this.totalAmount = totalAmount;
	this.attendences = attendences;
}

	private int attendencecount;

//    @JsonProperty("paymentMethod")
//    private PaymentMethod method;
    private String emp_name;
	private int setVisitCount;
	private String emp_department;
	
	private List attendence;
	private int emp_Id;
	private String mobile;
	private List<Salary> salary;
	 @JsonProperty("registrationDate")
	    private Date dateCreated;
    private float totalAmount;

    private List<Attendence> attendences = new ArrayList<>();

	public int getAttendencecount() {
		return attendencecount;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public String getEmp_department() {
		return emp_department;
	}

	public List getAttendence() {
		return attendence;
	}

	public int getEmp_Id() {
		return emp_Id;
	}

	public String getMobile() {
		return mobile;
	}

	public List<Salary> getSalary() {
		return salary;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public float getTotalAmount() {
		return totalAmount;
	}

	public List<Attendence> getAttendences() {
		return attendences;
	}

	public void setAttendencecount(int attendencecount) {
		this.attendencecount = attendencecount;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public void setEmp_department(String emp_department) {
		this.emp_department = emp_department;
	}

	public void setAttendence(List attendence) {
		this.attendence = attendence;
	}

	public void setEmp_Id(int emp_Id) {
		this.emp_Id = emp_Id;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setSalary(List<Salary> salaries) {
		this.salary = salaries;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setAttendences(List<Attendence> list) {
		this.attendences = list;
	}

	public void setVisitCount(int size) {
		// TODO Auto-generated method stub
		this.setVisitCount=size;
	}
    
    
}
