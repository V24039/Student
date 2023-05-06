package com.School.Student.service;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.School.Student.dto.ChangePassword;
import com.School.Student.dto.LoginDTO;
import com.School.Student.dto.StudentDTO;
import com.School.Student.entity.Student;
import com.School.Student.exceptions.StudentExceptions;
import com.School.Student.repository.StudentRepo;
/**
 * @author Venu
 *
 *  View Student profile
 *  Update the student profile
 *  Change password
 *  Get student marks
 *  Create a student
 *  Update student marks
 */
@Service
@PropertySource(value ={"classpath:message.properties"})
public class StudentService {

	@Autowired
	private StudentRepo repo;
	
	@Autowired
	Environment environment;
	
	public String login(@RequestBody LoginDTO dto) {
		return environment.getProperty("login.success");
	}
	
	//complete
	public HashMap<String, Integer> viewReport(Integer id) throws StudentExceptions {
		
		Optional<Student> optional =  repo.findById(id);
		Student student = optional.orElseThrow(() -> new StudentExceptions(environment.getProperty("student.notFound")));
		return new StudentDTO().entityToDto(student).getSubjectsMarks();
	}

	//complete
	public StudentDTO viewProfile(Integer id) throws StudentExceptions {
		
		Optional<Student> optional =  repo.findById(id);
		Student student = optional.orElseThrow(() -> new StudentExceptions(environment.getProperty("student.notFound")));
		return new StudentDTO().entityToDto(student);
	}

	//complete
	public String update(StudentDTO dto) throws StudentExceptions {
		
		Optional<Student> optional =  repo.findById(dto.getStudentId());
		Student student = optional.orElseThrow(() -> new StudentExceptions(environment.getProperty("student.notFound")));
		
		if(dto.getName()!=null)
			student.setName(dto.getName());
		else if(dto.getPhone()!=null) 
			student.setPhone(dto.getPhone());
		try{
			repo.save(student);
		}
		catch (Exception e) {
			throw new StudentExceptions(environment.getProperty("account.updateFail"));
		}
		return environment.getProperty("account.updateSuccess");
	}
	
	//error
	public String changePassword(Integer id, ChangePassword password) throws StudentExceptions {
		
		Optional<Student> optional =  repo.findById(id);
		Student student = optional.orElseThrow(() -> new StudentExceptions(environment.getProperty("student.notFound")));
		if(password.getOldPassword().equals(student.getPassword())) {
			student.setPassword(password.getNewPassword());
			try{
				repo.save(student);
			}catch (Exception e) {
				throw new StudentExceptions((environment.getProperty("account.passwordChangeFail")));
			}			
			return environment.getProperty("account.passwordChangeFail");
		}
		return environment.getProperty("account.passwordChangeSuccess");
	}

	//only teachers can access it
	//complete
	public String create(StudentDTO dto) throws StudentExceptions {
		
		Student student = new StudentDTO().dtoToEntity(dto);
		try{
			repo.save(student);
		}
		catch (Exception e) {
			throw new StudentExceptions(environment.getProperty("account.createFail"));
		}
		return environment.getProperty("account.createSuccess") + student.getStudentId() ;
	}
	
	//only teachers can access it
	public String updateMarks(Integer id, HashMap<String, Integer> subjectsMarks) throws StudentExceptions {

		Optional<Student> optional =  repo.findById(id);
		Student student = optional.orElseThrow(() -> new StudentExceptions(environment.getProperty("student.notFound")));

		student.setSubjectsMarks(subjectsMarks);
		repo.save(student);

		System.out.println(subjectsMarks);
		return environment.getProperty("account.passwordChangeSuccess");
	}

}
