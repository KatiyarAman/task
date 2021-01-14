package com.quokka.task;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.quokka.task.daoImpl.EmployeeDaoImpl;
import com.quokka.task.daoImpl.GenericRepositoryImpl;
import com.quokka.task.daoImpl.SalaryDaoImpl;
@EntityScan( basePackages = {"com.quokka.task.entity"})
@SpringBootApplication(scanBasePackages={"com.quokka.task.entity","com.quokka.task.controller","com.quokka.task.dto",
		"com.quokka.task.service","com.quokka.task.co","com.quokka.task.serviceImpl",
		"com.quokka.task.config","com.quokka.task.utils","com.quokka.task.report.service"
		,"com.quokka.task.daoImpl","com.quokka.task.repository","com.quokka.task.dao","com.quokka.task"})  
//@Configuration
@ComponentScan
@EnableJpaRepositories(basePackages="com.quokka.task.repository",basePackageClasses = {EmployeeDaoImpl.class,SalaryDaoImpl.class, GenericRepositoryImpl.class})
public class TaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskApplication.class, args);
	}
	@Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
