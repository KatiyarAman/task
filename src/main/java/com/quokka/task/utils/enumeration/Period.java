package com.quokka.task.utils.enumeration;

import com.quokka.task.model.exception.NotFoundException;

public enum Period {

    TODAY("TODAY"),
    YESTERDAY("YESTERDAY"),
    WEEKLY("WEEKLY"),
    MONTHLY("MONTHLY");

    private final String type;

    Period(String type) {
        this.type = type;
    }

    public static Period from(String period) {
        if (period.equalsIgnoreCase("today"))
            return TODAY;
        else if (period.equalsIgnoreCase("YESTERDAY"))
            return YESTERDAY;
        else if (period.equalsIgnoreCase("WEEKLY"))
            return WEEKLY;
        else if (period.equalsIgnoreCase("MONTHLY"))
            return MONTHLY;
        else
            throw new NotFoundException("Period not found exception");
    }

    public String getType() {
        return type;
    }
}
