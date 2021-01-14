package com.quokka.task.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.quokka.task.co.EmployeeCo;
import com.quokka.task.dto.*;
import com.quokka.task.entity.Attendence;
import com.quokka.task.entity.Employee;
import com.quokka.task.model.exception.BadRequestException;
import com.quokka.task.service.AttendenceService;
import com.quokka.task.service.EmployeeService;

import io.swagger.annotations.ApiOperation;
@RestController
@RequestMapping("/report")
public class ReportController {
	    private static final Logger log=LoggerFactory.getLogger(ReportController.class);
	    @Autowired
	    private AttendenceService attendenceService;
	    @Autowired
	    private EmployeeService empService;
	    
	    @ApiOperation(value = "showAll Employee by Head")
	    @ResponseBody
	   @RequestMapping(value = "/{emp_Id}", method = RequestMethod.GET,consumes = "application/json", produces = "application/json")
	    public List<Employee> ShowAll(@ModelAttribute Employee employee, BindingResult bindingResult,@PathVariable("emp_Id")String emp_Id) {
	             if (bindingResult.hasErrors())
	             throw new BadRequestException("Bad Request. Params Missing");
	             log.info("List Of Employee:");
	             empService.findbyColumn(emp_Id);	         	            
	             return null;
	    }
	    
	    	  	    
	    @ApiOperation(value = "Payment Report filter by date and tag like monthly")
	    @RequestMapping(value = "/payment/{emp_Id}/{emp_checkout}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	    @ResponseBody
	    public List<AttendenceDto> paymentReportFilter(@ModelAttribute Attendence attence,@PathVariable("emp_Id")  String emp_Id, 
	    		@PathVariable("emp_checkout")  String emp_checkout,BindingResult bindingResult ){
           
	    	List<AttendenceDto> attListDto=  attendenceService.getAttendenceDetails(emp_Id, emp_checkout);
            log.info("Report Controller :" +attListDto.size());
            return attListDto;
         
	    }
}
