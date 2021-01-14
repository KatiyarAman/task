package com.quokka.task.co;



import java.util.Date;

import com.quokka.task.utils.enumeration.Period;

public class FilterCO {

    private Date start;

    private Date end;

    
    private Period period;

    private String emp_Id;

    private String emp_name;

	public Date getStart() {
		return start;
	}

	public Date getEnd() {
		return end;
	}

	public Period getPeriod() {
		return period;
	}

	public String getEmp_Id() {
		return emp_Id;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public void setEmp_Id(String emp_Id) {
		this.emp_Id = emp_Id;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	

    public boolean isValidDateFilter() {
        return (this.start != null && this.end != null);
    }

    public boolean isValidPeriodFilter() {
        return (this.period != null);
    }

    public boolean isValidLocationFilter() {
        return (this.emp_Id != null && !this.emp_Id.isEmpty());
    }

    public boolean isValidNameFilter() {
        return (this.emp_name != null);
    } 
    
    
}

    