package com.quokka.task.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.quokka.task.co.EmployeeCo;



@Entity
@Table(name="employee")
public class Employee  implements Auditable{
	@Id
	@Column(name="emp_Id")
	private int emp_Id;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="e_Id")
	private int e_Id;
	
	@Column(name="em_Id")
	private String em_Id;
	
	@Column(name="emp_name")
	private String emp_name;
	@Column(name="emp_department")
	private String emp_department;
	
	@Column(unique = true, nullable = false)
    private String mobile;
	
	@Column(name = "is_deleted",updatable = true)
	  private boolean is_deleted = false;
	 
	@OneToMany(mappedBy="employeeattendence",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Attendence> attendence;
	@OneToMany(mappedBy="employee" ,fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Salary> salary;
	
	
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
    
	

	public Employee() {
		super();
		this.em_Id=generateEm_Id();
		this.is_deleted = false;
	}
	
	private String generateEm_Id() {
        return "C-" + new Date().getTime();
    }
	
	

	 public Employee(int emp_Id, int e_Id, String em_Id, String emp_name, String emp_department, String mobile,
			boolean is_deleted, List<Attendence> attendence, List<Salary> salary,Date dateCreated, Date lastModified,
			Date dateDeleted) {
		super();
		this.emp_Id = emp_Id;
		this.e_Id = e_Id;
		this.em_Id = generateEm_Id();
		this.emp_name = emp_name;
		this.emp_department = emp_department;
		this.mobile = mobile;
		this.is_deleted = is_deleted;
		this.attendence = attendence;
		this.salary = salary;
		this.dateCreated=dateCreated;
		this.lastModified = lastModified;
		this.dateDeleted = dateDeleted;
	}

	public Employee(EmployeeCo employeeCo) {
		 this.emp_Id =employeeCo.getEmp_Id();
			this.em_Id = generateEm_Id();
			this.emp_name =employeeCo.getEmp_name();
			this.emp_department =employeeCo.getEmp_department();
			this.attendence = employeeCo.getAttendence();
			this.salary =employeeCo.getSalary();
			this.is_deleted = false;
	    }
	 
	 
	
	public String getEm_Id() {
		return em_Id;
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
	
	public void setEm_Id(String em_Id) {
		this.em_Id = em_Id;
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

	public int getE_Id() {
		return e_Id;
	}

	public void setE_Id(int e_Id) {
		this.e_Id = e_Id;
	}



	public int getEmp_Id() {
		return emp_Id;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	

	

	
	
	
}
