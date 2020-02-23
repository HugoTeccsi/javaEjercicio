package com.pe.hatv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pe.hatv.model.JobLoggerDB;

@Repository
public interface IJobLoggerDB extends JpaRepository<JobLoggerDB, Long> {

}
