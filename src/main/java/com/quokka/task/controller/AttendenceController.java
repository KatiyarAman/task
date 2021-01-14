package com.quokka.task.controller;


import java.util.List;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.quokka.task.co.AttendenceCo;
import com.quokka.task.co.AttendenceUpdateCo;
import com.quokka.task.dto.AttendenceDto;
import com.quokka.task.model.exception.BadRequestException;
import com.quokka.task.service.AttendenceService;
import io.swagger.annotations.ApiOperation;


@Controller
@RequestMapping("/attendence")
public class AttendenceController {
  private static final Logger log=LoggerFactory.getLogger(AttendenceController.class);
  
  @Autowired
  private AttendenceService attendenceService;

  
  
  @ApiOperation(value = "Count Attendence")
  @ResponseBody
  @RequestMapping(value = "/all/{emp_Id}", method = RequestMethod.GET,consumes = "application/json", produces = "application/json")
   public List<AttendenceDto> CheckAttendenceById( @RequestBody @Valid AttendenceCo attendenceCo,
   		@PathVariable("emp_Id") String emp_Id, BindingResult bindingResult) {
	       
           if (bindingResult.hasErrors())
           throw new BadRequestException("Bad Request. Params Missing");
           List<AttendenceDto> attendenceDtoList=attendenceService.getCheckedAttendence(emp_Id);
           int sizeofList=attendenceDtoList.size();
           System.out.println("Size of List Is : "+sizeofList);
           return attendenceService.getCheckedAttendence(emp_Id);
   }
  @RequestMapping(value = "/check-in", method = RequestMethod.POST ,consumes = "application/json", produces = "application/json")
  @ResponseBody
   public AttendenceDto save( @ModelAttribute AttendenceCo attendenceCo,BindingResult bindingResult,
   		@RequestParam("emp_Id")  int emp_Id,@RequestParam("emp_month")String emp_month) {
           log.info("Attendence Controller :");
           System.out.println("Emp_Id: "+emp_Id);
           if (bindingResult.hasErrors())
           throw new BadRequestException("Bad Request. Params Missing");
           return attendenceService.save(attendenceCo); 
   }
  
  @ApiOperation(value = "Update Attendence")
  @ResponseBody
  @RequestMapping(value = "/{emp_Id}", method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
   public AttendenceDto update( @RequestBody @Valid AttendenceUpdateCo attendenceupdateCo,
   		@PathVariable("emp_Id")  String emp_Id, BindingResult bindingResult) {
           if (bindingResult.hasErrors())
           throw new BadRequestException("Bad Request. Params Missing");
           return attendenceService.update(attendenceupdateCo,emp_Id);
   }
  @ApiOperation(value = "Count Attendence")
  @ResponseBody
  @RequestMapping(value = "/count/{emp_Id}", method = RequestMethod.GET,consumes = "application/json", produces = "application/json")
   public List<AttendenceDto> Count( @RequestBody @Valid AttendenceUpdateCo attendenceupdateCo,
   		@PathVariable("emp_Id") String emp_Id, BindingResult bindingResult) {
	       String emp_checkout="checked-out";
           if (bindingResult.hasErrors())
           throw new BadRequestException("Bad Request. Params Missing");
           List<AttendenceDto> attendenceDtoList=attendenceService.getAttendenceDetails(emp_Id, emp_checkout);
           int sizeofList=attendenceDtoList.size();
           System.out.println("Size of List Is : "+sizeofList);
           return attendenceService.getAttendenceDetails(emp_Id, emp_checkout);
   }

  
}
