package com.quokka.task.daoImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import com.quokka.task.dao.SalaryDao;
import com.quokka.task.entity.Salary;

@Repository
public class SalaryDaoImpl extends GenericRepositoryImpl<Salary> implements SalaryDao,InitializingBean{
    private static final Logger log=LoggerFactory.getLogger(SalaryDaoImpl.class);
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		super.setEntity(Salary.class);
	}
	@Override
	public Salary get(String emp_Id) {
		// TODO Auto-generated method stub
		log.info("Employee Exist or not :"+emp_Id);
		return super.findByColumnIsDeleted("emp_Id", emp_Id);
	}

}
