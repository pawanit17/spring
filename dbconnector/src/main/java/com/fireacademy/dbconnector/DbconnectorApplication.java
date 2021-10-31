package com.fireacademy.dbconnector;

import com.fireacademy.dbconnector.jdbc.PersonJdbcDao;
import com.fireacademy.dbconnector.jdbc.entity.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class DbconnectorApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	PersonJdbcDao dao;
	public static void main(String[] args) {
		SpringApplication.run(DbconnectorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Get all data
		logger.info("FindAll Users");
		logger.info( "All users -> {}", dao.findAll() );

		// Get select data
		logger.info("FindById Users");
		logger.info( "All users -> {}", dao.findById(10002) );

		// Delete by ID
		logger.info("deleteById Users");
		logger.info( "All users -> {}", dao.deleteById(10003) );

		// Post delete search
		logger.info("After Delete FindAll Users");
		logger.info( "All users -> {}", dao.findAll() );

		// Insert
		logger.info("Insert Pavan");
		logger.info( "Insert Pavan -> {}", dao.insert(new Person(10005, "Pavan", "Chicago", new Date())) );
		logger.info( "After Insert All users -> {}", dao.findAll() );

		// Update
		logger.info("Update Pavan Users");
		logger.info( "Update Pavan -> {}", dao.update(new Person(10005, "Pavan Dittakavi", "Chicago", new Date())) );
		logger.info( "After Update All users -> {}", dao.findAll() );
	}
}
