package com.quokka.task.report;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.quokka.task.response.Response;
import com.quokka.task.utils.enumeration.ReportType;

public interface ReportFactory {

    public <T extends Response> ReportService<T> getInstance(ReportType reportType);
}
