package com.quokka.task.utils;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quokka.task.dto.AttendenceDto;
import com.quokka.task.dto.AttendenceReportDto;
import com.quokka.task.dto.EmployeeDto;
import com.quokka.task.dto.SalaryDto;
import com.quokka.task.entity.Attendence;
import com.quokka.task.entity.Employee;
import com.quokka.task.entity.Salary;





@Component
public class ObjectBinder {

	 @Autowired
	 private ModelMapper modelMapper;
	 
	  public EmployeeDto bindEmployee(Employee employee)
	  {
		      if(employee ==null)
			  return null;
			  EmployeeDto empDto=modelMapper.map(employee,EmployeeDto.class);			  
			  return empDto;
	  }
	  public AttendenceDto bindAttendence(Attendence attendence)
	  {
		      if(attendence ==null)
			  return null;
		      AttendenceDto attendenceDto=modelMapper.map(attendence,AttendenceDto.class);			  
			  return attendenceDto;
	  }
	  
	  public List<EmployeeDto> bindEmpList(List<Employee> employees)
	  {
	         if(employees == null)
	       	 return new ArrayList<>();
	         List<EmployeeDto> empDtoList=new ArrayList<>();
	         employees.forEach(
	    		 ed->{if(ed !=null)
	    		    empDtoList.add(new EmployeeDto(ed));	 
	    		 }
	    		);
		     return empDtoList;
	  }
	  public List<AttendenceDto> bindAttenedenceList(List<Attendence> attendence)
	  {
	        if(attendence == null)
	    	return new ArrayList<>();
	        List<AttendenceDto> atDtoList=new ArrayList<>();
	          attendence.forEach(
	    		 ed->{if(ed !=null)
	    			 atDtoList.add(new AttendenceDto(ed));	 
	    		 }
	    		);
		    return atDtoList;
	  }
//	  public AttendenceReportDto bindOrderReport(Attendence order, List<UserDTO> userDTOS, List<LocationDTO> locationDTOS, List<Transaction> transactions, List<Product> products, boolean isPaymentWaitingRecordsRequired) {
//	        OrderReportDTO orderReport = new OrderReportDTO(order);
//	        orderReport.setTransactions(bindTransaction(transactions, locationDTOS, userDTOS, isPaymentWaitingRecordsRequired));
//	        orderReport.setProductList(bindProduct(products));
//	        return orderReport;
//	    }

	    public AttendenceReportDto bindattendenceReport(List<Employee> list,List<Attendence> atlist,List<Salary> sallist)  {
	            if (list != null)
	             modelMapper.map(list,AttendenceReportDto.class);
	            modelMapper.map(atlist,AttendenceReportDto.class);
	            modelMapper.map(sallist,AttendenceReportDto.class);
	            return null;
	    }
	    public SalaryDto bindSalary(Salary salary)
		  {
			      if(salary ==null)
				  return null;
				  SalaryDto salaryDto=modelMapper.map(salary,SalaryDto.class);			  
				  return salaryDto;
		  }
	  
	    
}





