package com.quokka.task.dto;

import com.quokka.task.entity.Attendence;
import com.quokka.task.entity.Employee;

public class AttendenceDto {
	    private String att_Id;
	    private String emp_checkin;
		private String emp_checkout;
		private String emp_attendence;
		private String empdate;
		private String empmonth;
		private int emp_Id;
		private Employee employeeattendence;
		
		
		public AttendenceDto() {
			super();
		}
		public AttendenceDto(Attendence at) {
			this.emp_checkin = at.getEmp_checkin();
			this.emp_checkout = at.getEmp_checkout();
			this.emp_attendence = at.getEmp_attendence();
			this.empmonth = at.getEmpmonth();
			this.emp_Id = at.getEmp_Id();
			this.att_Id=at.getAtt_Id();
			
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
		public int getEmp_Id() {
			return emp_Id;
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
}
