package com.quokka.task.service;

import java.util.List;

import javax.validation.Valid;

import com.quokka.task.co.AttendenceCo;
import com.quokka.task.co.AttendenceUpdateCo;
import com.quokka.task.dto.AttendenceDto;
import com.quokka.task.entity.Attendence;
import com.quokka.task.entity.Pagination;


public interface AttendenceService {
	public AttendenceDto save(AttendenceCo AttendenceCo);
    public AttendenceDto get(int emp_Id);
	public AttendenceDto update(@Valid AttendenceUpdateCo attendenceupdateCo, String emp_Id);
	public List<AttendenceDto> getCheckedAttendence(String emp_Id);
	public List<AttendenceDto> getAttendenceDetails(String emp_Id,String check_out);

}
