package com.quokka.task.dao;

import java.util.List;

import com.quokka.task.co.AttendenceCo;
import com.quokka.task.entity.Attendence;
import com.quokka.task.entity.Employee;
import com.quokka.task.entity.Pagination;


public interface AttendenceDao {
	public Attendence save(AttendenceCo attendenceCo);
	public Attendence get(String emp_Id);
	public Attendence get(int emp_Id);
	public Attendence get(int emp_Id,String emp_checkIn);
	public List<Attendence> findAllByCustomer(Employee emloyee);
	public List<Attendence> findallByColumn(String emp_Id);
	public List<Attendence> findallColumnandId(String emp_Id,String check_out);
    

	public int findAllCount(int count);
}
