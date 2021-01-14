package com.quokka.task.entity;




import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.quokka.task.co.SalaryCo;


@Entity
@Table(name="salary")
public class Salary implements Auditable  {
    @Id
    @Column(name="s_id")
    private int s_id;
    @Column(name="salary_Id",nullable = false)
	 private String salary_Id;
    @Column(name="salary")
	 private String salary;
    @Column(name="netsalary")
	 private double netsalary;
    @Column(name="emp_Id")
    private int emp_Id;
    
    @Column(name = "is_deleted")
    private boolean is_deleted = false;
    @ManyToOne
    @JoinColumn(name="emp_sal")
    private Employee employee;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, nullable = false)
    @org.hibernate.annotations.CreationTimestamp
    private Date dateCreated;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = true)
    @org.hibernate.annotations.UpdateTimestamp
    private Date lastModified;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date dateDeleted;

    
	

	public Salary() {
		this.salary_Id=generateSalaryId();
		this.is_deleted=false;
	}

	 public Salary(int s_id, String salary_Id, String salary, double netsalary, int empid, Employee employee) {
		super();
		this.s_id = s_id;
		this.salary_Id =generateSalaryId();
		this.salary = salary;
		this.netsalary = netsalary;
		this.emp_Id = empid;
		this.employee = employee;
	}

	public Salary(SalaryCo salaryCO) {
	        this.salary_Id = generateSalaryId();
	        this.salary = salaryCO.getSalary();
	        this.netsalary = salaryCO.getNetsalary();
	        this.emp_Id = salaryCO.getEmp_Id();
	        
	
	    }
	
	
	public String generateSalaryId() {
        return "ADD-" + new Date().getTime();
    }

	public int getS_id() {
		return s_id;
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

	public Employee getEmployee() {
		return employee;
	}

	public void setS_id(int s_id) {
		this.s_id = s_id;
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


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	

	public Date getLastModified() {
		return lastModified;
	}

	public Date getDateDeleted() {
		return dateDeleted;
	}

	

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public void setDateDeleted(Date dateDeleted) {
		this.dateDeleted = dateDeleted;
	}

	@Override
	public int getEmp_Id() {
		// TODO Auto-generated method stub
		return emp_Id;
	}

	@Override
	public Date getDateCreated() {
		// TODO Auto-generated method stub
		return dateCreated;
	}

	public void setEmp_Id(int emp_Id) {
		this.emp_Id = emp_Id;
	}

	public boolean isIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	
   
    

	
}
