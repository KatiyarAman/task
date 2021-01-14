package com.quokka.task.dto;

import com.quokka.task.entity.Attendence;
import com.quokka.task.entity.Employee;

public class AttendenceReportDto {
	private String att_Id;
    private String emp_checkin;
	private String emp_checkout;
	private String emp_attendence;
	private String empdate;
	private String empmonth;
	private int empid;
	private Employee employeeattendence;
	
	
	public AttendenceReportDto() {
		super();
	}
	public AttendenceReportDto(Attendence at) {
		this.emp_checkin = at.getEmp_checkin();
		this.emp_checkout = at.getEmp_checkout();
		this.emp_attendence = at.getEmp_attendence();
		this.empmonth = at.getEmpmonth();
		this.empid = at.getEmp_Id();
		
	}
	public String getAtt_Id() {
		return att_Id;
	}
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
	public String getEmpmonth() {
		return empmonth;
	}
	public int getEmpid() {
		return empid;
	}
	public Employee getEmployeeattendence() {
		return employeeattendence;
	}
	public void setAtt_Id(String att_Id) {
		this.att_Id = att_Id;
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
	public void setEmpmonth(String empmonth) {
		this.empmonth = empmonth;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public void setEmployeeattendence(Employee employeeattendence) {
		this.employeeattendence = employeeattendence;
	}
}
