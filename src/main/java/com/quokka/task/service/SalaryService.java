package com.quokka.task.service;

import com.quokka.task.co.SalaryUpdateCo;
import com.quokka.task.dto.SalaryDto;

public interface SalaryService {

	 public SalaryDto update(SalaryUpdateCo salaryupdateco,String emp_Id);
}
