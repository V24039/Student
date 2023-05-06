package com.School.Student;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.School.Student.entity.Student;
import com.School.Student.repository.StudentRepo;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class StudentApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}

	@Autowired
	private StudentRepo  repo;
	
	@Override
	public void run(String... args) throws Exception {
		HashMap<String, Integer> marks = new HashMap<>();
		marks.put("Maths", 24);
		marks.put("Science", 34);
		marks.put("English", 57);
		marks.put("Kannada", 67);
		marks.put("C.S", 66);

		List<Student> list = Arrays.asList(new Student(1, "Venu", "abc@gmail.com",1234567890,"abcdef", marks, "10"),
				new Student(2, "Vijaya", "abc@gmail.com", 1234895620, "abcdef", marks, "10"),
				new Student(3, "Raghu", "abc@gmail.com", 1462567890,"abcdef",marks, "10"));
		
		repo.saveAll(list);
	}

}
