package com.quokka.task.dto;

import com.quokka.task.entity.Salary;

public class SalaryDto {

	private String salary_Id;
	private String salary;
	private double netsalary;
	private int emp_Id;
	
	private int s_id;
	
	 public SalaryDto() {
		super();
	}
	SalaryDto(Salary salary)
	 {
		 this.emp_Id=salary.getEmp_Id();
		 this.salary=salary.getSalary();
		 this.netsalary=salary.getNetsalary();
		 this.salary_Id=salary.getSalary_Id();
	 }
	public int getS_id() {
		return s_id;
	}
	public void setS_id(int s_id) {
		this.s_id = s_id;
	}
	public String getSalary_Id() {
		return salary_Id;
	}
	public String getSalary() {
		return salary;
	}
	public double getNetsalary() {
		return netsalary;
	}
	public void setSalary_Id(String salary_Id) {
		this.salary_Id = salary_Id;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public void setNetsalary(double netsalary) {
		this.netsalary = netsalary;
	}
	public int getEmp_Id() {
		return emp_Id;
	}
	public void setEmp_Id(int emp_Id) {
		this.emp_Id = emp_Id;
	}
	
	
}
