package com.db34.WebArchive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.db34.WebArchive.domains.db.ArchiveItemJdbcRepository;

@SpringBootApplication
public class WebArchiveApplication implements CommandLineRunner {

	@Autowired
	ArchiveItemJdbcRepository repository;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(WebArchiveApplication.class, args);
	}

	@Override
    public void run(String...args) throws Exception {
		logger.debug("Application is starting up");
//        logger.info("Student id 10001 -> {}", repository.findById(10001 L));
//        logger.info("All users 1 -> {}", repository.findAll());
//        logger.info("Inserting -> {}", repository.insert(new Student(10010 L, "John", "A1234657")));
//        logger.info("Update 10001 -> {}", repository.update(new Student(10001 L, "Name-Updated", "New-Passport")));
//        repository.deleteById(10002L);
//        logger.info("All users 2 -> {}", repository.findAll());
    }
}
