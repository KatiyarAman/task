package com.quokka.task.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.quokka.task.co.AttendenceCo;

@Entity
@Table(name="attendence")
public class Attendence implements Auditable{

	@Id
	
	@Column(name="at_id")
	private int id;
	@Column(name="att_Id")
	private String att_Id;
	
	@Column(name="emp_checkin")
	private String emp_checkin;
	@Column(name="emp_checkout")
	private String emp_checkout;
	@Column(name="emp_attendence")
	private String emp_attendence;
	@Column(name="emp_date")
	private String empdate;
	@Column(name="emp_month")
	private String empmonth;
	
	@Column(name="emp_Id")
	private int emp_Id;
	@Column(name = "is_deleted")
    private boolean is_deleted = false;
	
	@ManyToOne
	@JoinColumn(name="emp_att")
    private Employee employeeattendence;

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
	public Attendence() {
		super();
		this.att_Id = generateAtt_Id();
		this.is_deleted=false;
	}
	private String generateAtt_Id() {
        return "C-" + new Date().getTime();
    }

	public Attendence(String att_Id, String emp_checkin, String emp_checkout, String emp_attendence, String empdate,
			String empmonth, int emp_Id, Employee employeeattendence) {
		super();
		this.att_Id = generateAtt_Id();
		this.emp_checkin = emp_checkin;
		this.emp_checkout = emp_checkout;
		this.emp_attendence = emp_attendence;
		this.empdate = empdate;
		this.empmonth = empmonth;
		this.emp_Id = emp_Id;
		this.employeeattendence = employeeattendence;
	}
	public Attendence(AttendenceCo attendenceCo) {
		this.att_Id = generateAtt_Id();
		this.emp_checkin = attendenceCo.getEmp_checkin();
		this.emp_checkout = attendenceCo.getEmp_checkout();
		this.emp_attendence = attendenceCo.getEmp_attendence();
		this.empdate = attendenceCo.getEmpdate();
		this.empmonth = attendenceCo.getEmp_month();
		this.emp_Id = attendenceCo.getEmp_Id();
		this.employeeattendence = attendenceCo.getEmployeeattendence();
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
	public void setEmp_Id(int emp_Id) {
		this.emp_Id = emp_Id;
	}
	public void setEmployeeattendence(Employee employeeattendence) {
		this.employeeattendence = employeeattendence;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

	public boolean isIs_deleted() {
		return is_deleted;
	}
	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}
	@Override
	public Date getDateCreated() {
		// TODO Auto-generated method stub
		return this.dateCreated=dateCreated;
	}
	@Override
	public int getEmp_Id() {
		// TODO Auto-generated method stub
		return emp_Id;
	}
	


	
	
}
