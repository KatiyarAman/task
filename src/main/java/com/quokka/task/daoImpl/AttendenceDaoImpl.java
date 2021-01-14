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

import com.quokka.task.co.AttendenceCo;
import com.quokka.task.dao.AttendenceDao;

import com.quokka.task.entity.Attendence;
import com.quokka.task.entity.Employee;
import com.quokka.task.entity.Pagination;

@Repository
public class AttendenceDaoImpl extends GenericRepositoryImpl<Attendence> implements AttendenceDao, InitializingBean {
    private static final Logger log=LoggerFactory.getLogger(AttendenceDaoImpl.class);
	private Attendence att=new Attendence();

	
	@Override
	public Attendence get(int emp_Id) {
		// TODO Auto-generated method stub
		return super.findByColumn("emp_Id", emp_Id);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		 super.setEntity(Attendence.class);
	}

	@Override
	public Attendence save(AttendenceCo attendenceCo) {
		// TODO Auto-generated method stub
		System.out.println(attendenceCo.getEmp_Id());
		System.out.println(attendenceCo.getEmp_month());
		att.setEmp_attendence("N/A");
		att.setEmp_checkin("Checked-in");
		att.setEmp_checkout("Wait-for");
		att.setEmpmonth(attendenceCo.getEmp_month());
		att.setEmp_Id(attendenceCo.getEmp_Id());
		return super.save(att);
	}

	@Override
	public Attendence get(String emp_Id) {
		// TODO Auto-generated method stub
		log.info("String type Emp_Id: " +emp_Id);
		return super.findByColumn("emp_Id", emp_Id);
	}

	@Override
	public Attendence get(int emp_Id, String emp_checkIn) {
		// TODO Auto-generated method stub
		log.info("FinByColumnAndId :DaolImpl: "+emp_Id+ " "+emp_checkIn);
		return super.findByColumnAndId("emp_Id", emp_Id,"emp_checkin",emp_checkIn);
	}

	@Override
	public List<Attendence> findAllByCustomer(Employee emloyee) {
		// TODO Auto-generated method stub
		  log.info("finding all  attendence by employee {}", emloyee);
	        List<Attendence> attendences = new ArrayList<>();
	        try (Session session = getCurrentSession()) {
	        	attendences = session.createQuery(getCriteriaQuery(session, "employee", emloyee)).getResultList();
	        } catch (Exception exp) {
	            exp.printStackTrace();
	            log.error("Exception while getting placed orders");
	        }
	        return attendences;
	}

	@Override
	public List<Attendence> findallByColumn(String emp_Id) {
		// TODO Auto-generated method stub
		return super.findAllByColumn("emp_Id",emp_Id);
	}

	@Override
	public List<Attendence> findallColumnandId(String emp_Id, String check_out) {
		// TODO Auto-generated method stub
		log.info("get checked out employee list only :"+emp_Id + " " +check_out);
		return super.findByColuAndId("emp_Id",emp_Id,"emp_checkout", check_out);
	}

	@Override
	public int findAllCount( int count) {
		// TODO Auto-generated method stub
		return super.count();
	}


}
