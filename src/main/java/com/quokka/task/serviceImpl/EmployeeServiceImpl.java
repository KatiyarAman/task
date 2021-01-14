package com.quokka.task.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quokka.task.co.EmployeeCo;
import com.quokka.task.co.EmployeeUpdateCo;
import com.quokka.task.dto.AttendenceReportDto;
import com.quokka.task.dto.EmployeeDto;
import com.quokka.task.dto.EmployeeReportDto;
import com.quokka.task.entity.Employee;
import com.quokka.task.model.exception.CustomerNotFoundException;
import com.quokka.task.model.exception.DuplicateRecordException;
import com.quokka.task.repository.EmployeeRepository;
import com.quokka.task.service.EmployeeService;
import com.quokka.task.utils.ObjectBinder;



@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
      @Autowired
	  private EmployeeRepository empDao;
      @Autowired
      private ObjectBinder objectBinder;
	@Override
	public EmployeeDto save(EmployeeCo employeeCo) {
		// TODO Auto-generated method stub		
		return objectBinder.bindEmployee(empDao.save(employeeCo));
	}
	@Override
	public boolean isExist(String mobile) {
		// TODO Auto-generated method stub
		System.out.println("Mobile"+findByMobile(mobile));
		return findByMobile(mobile) !=null;
	}

	@Override
	public EmployeeDto findByMobile(String mobile) {
		// TODO Auto-generated method stub
		return objectBinder.bindEmployee(empDao.findByMobile(mobile));
	}
	@Override
	public EmployeeDto update(EmployeeUpdateCo employeeUpdateCo, String em_Id) {
		// TODO Auto-generated method stub
		Employee employee = empDao.get(em_Id);
        if (employee == null)
            throw new CustomerNotFoundException("Employee not found by this em_Id " + em_Id);

        if (!employee.getMobile().equals(employeeUpdateCo.getMobile()) && empDao.findByMobile(employeeUpdateCo.getMobile()) != null)
            throw new DuplicateRecordException("Employee already exists with this mobile " + employeeUpdateCo.getMobile());
        employee.setMobile(employeeUpdateCo.getMobile());
        employee.setEmp_department(employeeUpdateCo.getEmp_department());
        employee.setEmp_name(employeeUpdateCo.getEmp_name());
        Employee updatedEmployee=empDao.save(employee);
        return objectBinder.bindEmployee(updatedEmployee);
	}
	@Override
	public EmployeeDto get(String emp_Id) {
		// TODO Auto-generated method stub
		return objectBinder.bindEmployee(empDao.get(emp_Id));
	}
	@Override
	public EmployeeDto get(int emp_Id) {
		// TODO Auto-generated method stub
		return objectBinder.bindEmployee(empDao.get(emp_Id));
	}
	@Override
	public List<EmployeeDto> ShowAll() {
		// TODO Auto-generated method stub
		return objectBinder.bindEmpList(empDao.ShowAll());
		
	}
	@Override
	public void softDelete(int emp_Id) {
		// TODO Auto-generated method stub
		empDao.softdelete(emp_Id);;
	}
	@Override
	public List<Employee> findbyColumn(String emp_Id) {
		// TODO Auto-generated method stub
		return empDao.findbyColumn(emp_Id);
	}
	
	

}
