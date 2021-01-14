package com.quokka.task.daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import org.springframework.stereotype.Repository;

import com.quokka.task.co.EmployeeCo;
import com.quokka.task.dao.EmployeeDao;

import com.quokka.task.entity.Attendence;
import com.quokka.task.entity.Employee;
import com.quokka.task.entity.Salary;


@Repository
public class EmployeeDaoImpl extends GenericRepositoryImpl<Employee> implements EmployeeDao, InitializingBean {
	private static final Logger log=LoggerFactory.getLogger(EmployeeDaoImpl.class);
	
	private Employee emp= new Employee();

	private Salary sal=new Salary();
	private List<Salary> sallist=new ArrayList<Salary>();
	private Attendence att=new Attendence();
	private List<Attendence> attlist=new ArrayList<Attendence>();

	@Override
	public Employee save(EmployeeCo employeeCo) {
		// TODO Auto-generated method stub		
		log.info("New User Entering:"+employeeCo);
		System.out.println(employeeCo.getEmp_Id());
		emp.setEmp_department(employeeCo.getEmp_department());
		emp.setEmp_name(employeeCo.getEmp_name());
		emp.setEmp_Id(employeeCo.getEmp_Id());
		emp.setMobile(employeeCo.getMobile());
		emp.setIs_deleted(false);
		// cas cadding implementation for salary table
		String obj="0";
		sal.setSalary(obj);
		sal.setNetsalary(0);
		sal.setEmp_Id(employeeCo.getEmp_Id());
		sallist.add(sal);
		//cascadding implementation for attendence
		String empmonth="1";
		att.setEmp_attendence("N/A");
		att.setEmp_checkin("N/A");
		att.setEmp_checkout("N/A");
		att.setEmpmonth(empmonth);
		att.setEmp_Id(employeeCo.getEmp_Id());
		attlist.add(att);
		
		emp.setSalary(sallist);
		emp.setAttendence(attlist);
		
			return super.save(emp);
		
	}
	@Override
	public Employee findByMobile(String mobile) {
		// TODO Auto-generated method stub
		log.info("Duplication :"+mobile);

		return super.findByColumn("mobile", mobile);
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		 super.setEntity(Employee.class);
	}
	@Override
	public Employee get(String emp_Id) {
		// TODO Auto-generated method stub
		return super.findByColumn("emp_Id", emp_Id);
	}
	@Override
	public Employee get(int emp_Id) {
		// TODO Auto-generated method stub
		return super.findByColumn("emp_Id", emp_Id);
	}
	@Override
	public List<Employee> ShowAll() {
		// TODO Auto-generated method stub
		log.info("Getting List of Entity: ");
		return super.list();
	}
	@Override
	public void softdelete(int emp_Id) {
		// TODO Auto-generated method stub
		log.info("Soft Delete by Emp_Id :" +emp_Id);
		 super.setDeleted(emp_Id);;
	}
	@Override
	public List<Employee> findbyColumn(String emp_Id) {
		// TODO Auto-generated method stub
		List<Employee> mobilewarehouse=null;
		
		
			try (Session session = getCurrentSession()) {
				 
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
			Root<Employee> rootEmp = criteriaQuery.from(Employee.class);
			Root<Attendence> rootDept = criteriaQuery.from(Attendence.class);
			Root<Salary> rootSal = criteriaQuery.from(Salary.class);
			criteriaQuery.multiselect(rootEmp,rootDept,rootSal);
			criteriaQuery.where(builder.equal(rootEmp.get("emp_Id"),emp_Id),
					builder.equal(rootDept.get("emp_Id"),emp_Id),
					builder.equal(rootSal.get("emp_Id"),emp_Id));
			Query query = session.createQuery(criteriaQuery);
			List<Object[]> resultList = query.getResultList();
			mobilewarehouse = query.getResultList();
			for (Object[] objects : resultList) {
				Employee employee=(Employee)objects[0];
				System.out.println(employee.getEmp_Id()+"\t"+employee.getEmp_name()+"\t"
				+employee.getEmp_department());
				Attendence department=(Attendence)objects[1];
				
				System.out.println("\t"+department.getEmp_attendence()+"\t"
						+department.getEmp_checkin()+"\t"+department.getEmp_checkout());
				
		           } 
			}
			
		

	return mobilewarehouse;
	}
	
	

}
