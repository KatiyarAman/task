package com.quokka.task.controller;


import java.util.List;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.quokka.task.co.EmployeeCo;
import com.quokka.task.co.EmployeeUpdateCo;
import com.quokka.task.dto.EmployeeDto;
import com.quokka.task.entity.Employee;
import com.quokka.task.service.EmployeeService;
import com.quokka.task.model.exception.BadRequestException;
import com.quokka.task.model.exception.DuplicateRecordException;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	  private static final Logger log=LoggerFactory.getLogger(EmployeeController.class);
	  @Autowired
	  private EmployeeService empService;
	  
	  @RequestMapping(value = "/save", method = RequestMethod.POST)
	  @ResponseBody
	  public EmployeeDto save (@ModelAttribute EmployeeCo employeeCo,BindingResult bindingResult,
			  @RequestParam("emp_name")String name,@RequestParam("mobile")String mobile,
			@RequestParam("emp_department")String dpt,@RequestParam("emp_Id")int emp_Id){
              log.info("Controller :");
               if (bindingResult.hasErrors())
               throw new BadRequestException("Bad Request. Params Missing");
               if (empService.isExist(employeeCo.getMobile()))
               throw new DuplicateRecordException("Customer already exists by mobile number " + employeeCo.getMobile());
	           return empService.save(employeeCo);
	    }
	  
	   @ApiOperation(value = "Update customer")
	   @ResponseBody
	   @RequestMapping(value = "/{emp_Id}", method = RequestMethod.PUT,consumes = "application/json", produces = "application/json")
	    public EmployeeDto update( @RequestBody @Valid EmployeeUpdateCo employeeUpdateCo,
	    		@PathVariable("emp_Id")  String em_Id, BindingResult bindingResult) {
	            if (bindingResult.hasErrors())
	            throw new BadRequestException("Bad Request. Params Missing");
	            return empService.update(employeeUpdateCo,em_Id);
	    }
	  
	    @ApiOperation(value = "showAll Employee by Head")
	    @ResponseBody
	   @RequestMapping(value = "/show all", method = RequestMethod.GET,consumes = "application/json", produces = "application/json")
	    public List<EmployeeDto> ShowAll(@ModelAttribute Employee employee, BindingResult bindingResult) {
	             if (bindingResult.hasErrors())
	             throw new BadRequestException("Bad Request. Params Missing");
	             log.info("List Of Employee:");
	             List<EmployeeDto> crunchifyList=empService.ShowAll();
	             System.out.println("Size of list :"+crunchifyList.size());
	             return crunchifyList;
	    }
	  
	   @ApiOperation(value = "Delete Employee")
	   @ResponseBody
	   @RequestMapping(value = "/{emp_Id}", method = RequestMethod.DELETE,consumes = "application/json", produces = "application/json")
	    public void softDelete( @RequestBody @Valid EmployeeCo employeeCo,
	    		@PathVariable("emp_Id")  int emp_Id, BindingResult bindingResult) {
	            if (bindingResult.hasErrors())
	            throw new BadRequestException("Bad Request. Params Missing");
	            empService.softDelete(emp_Id);
	    }
}
