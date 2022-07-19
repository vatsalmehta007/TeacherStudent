package com.example.TeacherStudent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication

public class TeacherStudentApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeacherStudentApplication.class, args);
	}

}
