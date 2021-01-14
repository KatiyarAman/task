package com.quokka.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quokka.task.dao.AttendenceDao;

import com.quokka.task.entity.Attendence;


public interface AttendenceRepository extends JpaRepository<Attendence, Integer>,AttendenceDao {

}
