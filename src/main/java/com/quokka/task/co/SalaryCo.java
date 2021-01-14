package com.quokka.task.co;

import javax.validation.constraints.NotBlank;

import org.springframework.lang.NonNull;

public class SalaryCo {
	@NotBlank
    @NonNull
    private String salary;
	private double netsalary;
	private int emp_Id;
	
	public String getSalary() {
		return salary;
	}
	public double getNetsalary() {
		return netsalary;
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

