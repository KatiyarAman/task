package com.quokka.task.serviceImpl;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quokka.task.co.SalaryUpdateCo;
import com.quokka.task.dao.SalaryDao;
import com.quokka.task.dto.SalaryDto;
import com.quokka.task.entity.Employee;
import com.quokka.task.entity.Salary;
import com.quokka.task.model.exception.CustomerNotFoundException;
import com.quokka.task.model.exception.DuplicateRecordException;
import com.quokka.task.repository.SalaryRepositroy;
import com.quokka.task.service.SalaryService;
import com.quokka.task.utils.ObjectBinder;

@Service
@Transactional
public class SalaryServiceImpl implements SalaryService {
      private static final Logger log=LoggerFactory.getLogger(SalaryServiceImpl.class); 
      @Autowired
      private SalaryRepositroy salaryDao;
      @Autowired
      private ObjectBinder objectBinder;
@Override
public SalaryDto update(SalaryUpdateCo salaryupdateco, String emp_Id) {
	//TODO Auto-generated method stub
	log.info("Salary updation by Emp_Id :" +emp_Id);
	 Salary semp_Id=salaryDao.get(emp_Id);
	 if(semp_Id == null)
      throw new CustomerNotFoundException("Salary not found by this emp_Id " + emp_Id);
	  semp_Id.setNetsalary(salaryupdateco.getNetsalary());
	  semp_Id.setSalary(salaryupdateco.getSalary());
	  Salary updateSalary=salaryDao.save(semp_Id);
	  return objectBinder.bindSalary(updateSalary);
    }
	
}
