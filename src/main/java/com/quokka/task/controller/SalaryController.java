package com.quokka.task.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quokka.task.co.EmployeeUpdateCo;
import com.quokka.task.co.SalaryUpdateCo;
import com.quokka.task.dto.AttendenceDto;
import com.quokka.task.dto.EmployeeDto;
import com.quokka.task.dto.SalaryDto;
import com.quokka.task.model.exception.BadRequestException;
import com.quokka.task.service.AttendenceService;
import com.quokka.task.service.SalaryService;

import io.swagger.annotations.ApiOperation;
@Controller
@RequestMapping("/salary")
public class SalaryController {
    public static final Logger Log= LoggerFactory.getLogger(SalaryController.class);	
    @Autowired
    private SalaryService salaryService;
    @Autowired
    private AttendenceService attendenceService;
      
       @ApiOperation(value = "Update salary")
	   @ResponseBody
	   @RequestMapping(value = "/{emp_Id}", method = RequestMethod.PUT,consumes = "application/json", produces = "application/json")
	    public SalaryDto update( @RequestBody @Valid SalaryUpdateCo salaryUpdateCo,
	    		@PathVariable("emp_Id")  String emp_Id, BindingResult bindingResult) {
    	        String emp_checkout="checked-out";
	            if (bindingResult.hasErrors())
	            throw new BadRequestException("Bad Request. Params Missing");
	            List<AttendenceDto> attendenceDtoList=attendenceService.getAttendenceDetails(emp_Id, emp_checkout);
	            int sizeofList=attendenceDtoList.size();
	             String salary= salaryUpdateCo.getSalary();
	             int i=Integer.parseInt(salary);
	             int totalsalary=i*sizeofList;
	            salaryUpdateCo.setNetsalary(totalsalary);
	            return salaryService.update(salaryUpdateCo, emp_Id);
	    }
}
