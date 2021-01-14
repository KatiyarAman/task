package com.quokka.task.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quokka.task.co.AttendenceCo;
import com.quokka.task.co.AttendenceUpdateCo;
import com.quokka.task.dto.AttendenceDto;
import com.quokka.task.entity.Attendence;
import com.quokka.task.entity.Employee;
import com.quokka.task.entity.Pagination;
import com.quokka.task.model.exception.CustomerNotFoundException;
import com.quokka.task.model.exception.DuplicateRecordException;
import com.quokka.task.repository.AttendenceRepository;
import com.quokka.task.repository.EmployeeRepository;
import com.quokka.task.service.AttendenceService;
import com.quokka.task.utils.ObjectBinder;
@Service
@Transactional
public class AttendenceServiceImpl implements AttendenceService {
  
	  private static final Logger log =LoggerFactory.getLogger(AttendenceServiceImpl.class);
	  private Attendence attendence= new  Attendence();
	  @Autowired
	  private AttendenceRepository attendenceDao;	 
	  @Autowired
	  private EmployeeRepository empDao;
      @Autowired
      private ObjectBinder objectBinder;
     
	@Override
	public AttendenceDto save(AttendenceCo AttendenceCo) {
		// TODO Auto-generated method stub
		int emp_Id=AttendenceCo.getEmp_Id();
		log.info("Employee Exits In the Table or Not With :"+emp_Id);
		Employee employee = empDao.get(emp_Id);
        if (employee == null)
            throw new CustomerNotFoundException("Employee not found by this emp_Id " + emp_Id);
		return objectBinder.bindAttendence(attendenceDao.save(AttendenceCo));
	}
	
	@Override
	public AttendenceDto get(int emp_Id) {
		// TODO Auto-generated method stub
		log.info("Count the attendence :");
		Long count=attendenceDao.count();
		System.out.println(" "+count);
		return objectBinder.bindAttendence(attendenceDao.get(emp_Id));
	}
   
	@Override
	public AttendenceDto update(@Valid AttendenceUpdateCo attendenceupdateCo, String emp_Id) {
		// TODO Auto-generated method stub
		log.info("Checked out :"+emp_Id );
		Employee employee = empDao.get(emp_Id);
        if (employee == null)
            throw new CustomerNotFoundException("Employee not found by this emp_Id " + emp_Id);
        
          attendence.setEmp_Id(attendenceupdateCo.getEmp_Id());
          attendence.setEmpmonth(attendenceupdateCo.getEmp_month());
          attendence.setEmp_checkin(attendenceupdateCo.getEmp_checkin());
          attendence.setEmp_checkout(attendenceupdateCo.getEmp_checkout());
          attendence.setEmp_attendence("N/A");
          log.info(" checked "+attendenceupdateCo.getEmp_checkin());
          log.info("Checked -Out :"+attendenceupdateCo.getEmp_checkout());
          log.info("empid"+attendenceupdateCo.getEmp_Id());
          log.info("empid"+attendenceupdateCo.getEmp_month());
        Attendence updatedAttendence=attendenceDao.save(attendence);
        return objectBinder.bindAttendence(updatedAttendence);
        //return null;

	}

	@Override
	public List<AttendenceDto> getCheckedAttendence(String emp_Id) {
		// TODO Auto-generated method stub
		Employee employee = empDao.get(emp_Id);
        if (employee == null)
            throw new CustomerNotFoundException("Employee not found by this emp_Id " + emp_Id);
		log.info("Get Checked Employee Details :" +emp_Id);	
		return objectBinder.bindAttenedenceList(attendenceDao.findallByColumn(emp_Id));

	}

	@Override
	public List<AttendenceDto> getAttendenceDetails(String emp_Id, String check_out) {
		// TODO Auto-generated method stub
		Employee employee = empDao.get(emp_Id);
        if (employee == null)
            throw new CustomerNotFoundException("Employee not found by this emp_Id " + emp_Id);
		log.info("get list of checked-out attendence by employee "+emp_Id + " "+check_out);
		return  objectBinder.bindAttenedenceList(attendenceDao.findallColumnandId(emp_Id, check_out));
	}

	

}
