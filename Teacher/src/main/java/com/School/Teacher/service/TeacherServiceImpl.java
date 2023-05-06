package com.School.Teacher.service;

import com.School.Teacher.dto.ChangePassword;
import com.School.Teacher.dto.TeacherDTO;
import com.School.Teacher.entity.Teacher;
import com.School.Teacher.repository.TeacherRepo;
import com.School.Teacher.utilities.TeacherExceptions;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@PropertySource("classpath:messages.properties")
public class TeacherServiceImpl implements TeacherService{
	
	@Autowired
	private TeacherRepo repo;
	
	@Autowired
	Environment  environment;
	
	@Autowired
	RestTemplate template; 

	//Complete
	@Override
	public TeacherDTO viewProfile(Integer id) throws TeacherExceptions {
		Optional<Teacher> optional =  repo.findById(id);
		Teacher Teacher = optional.orElseThrow(() -> new TeacherExceptions(environment.getProperty("teacher.service.absent")));
		return new TeacherDTO().entityToDto(Teacher);
	}

	//Complete
	@Override
	public String create(TeacherDTO dto) throws TeacherExceptions {
		Teacher Teacher = new TeacherDTO().dtoToEntity(dto);
		repo.save(Teacher);

		String msg = environment.getProperty("teacher.account.create") ;
		System.out.println(msg);
		return msg;
	}

	//Complete
	@Override
	public String update(TeacherDTO dto) throws TeacherExceptions {
		Optional<Teacher> optional =  repo.findById(dto.getTeacherId());
		Teacher Teacher = optional.orElseThrow(() -> new TeacherExceptions(environment.getProperty("teacher.service.absent")));
		
		if(dto.getTeacherName()!=null)
			Teacher.setTeacherName(dto.getTeacherName());
		else if(dto.getPhone()!=null) 
			Teacher.setPhone(dto.getPhone());
		try{
			repo.save(Teacher);
		}
		catch (Exception e) {
			throw new TeacherExceptions(environment.getProperty("teacher.service.update.unsuccessfull "));
		}
		return environment.getProperty("teacher.update.success");
	}

	@Override
	public String changePassword(Integer id, ChangePassword password) throws TeacherExceptions { 
				
		Optional<Teacher> optional =  repo.findById(id);
		Teacher Teacher = optional.orElseThrow(() -> new TeacherExceptions(environment.getProperty("teacher.service.absent")));
		if(password.getOldPassword().equals(Teacher.getPassword())) {
			Teacher.setPassword(password.getNewPassword());
			try{
				repo.save(Teacher);
			}catch (Exception e) {
				throw new TeacherExceptions(environment.getProperty("teacher.service.passwordUpadte.unsuccessfull"));
			}
			return environment.getProperty("teacher.service.passwordUpadte.success");
		}
		return environment.getProperty("teacher.password.incorrect");
	}

	//complete
	@Override
	public String addStudent(Integer id, List<Integer> studentsId) throws TeacherExceptions {
		Optional<Teacher> optional =  repo.findById(id);
		Teacher teacher = optional.orElseThrow(() -> new TeacherExceptions(environment.getProperty("teacher.service.absent")));
		List<Integer> presentIds = teacher.getStudentsId();
		studentsId.forEach(e -> presentIds.add(e));
		System.out.println(studentsId);
		teacher.setStudentsId(presentIds);
//		System.out.println(teacher.getStudentsId());
		try{
			repo.save(teacher);
		}catch (Exception e) {
			throw new TeacherExceptions(environment.getProperty("teacher.student.fail"));
		}
//		System.out.println(teacher.getStudentsId());
		return environment.getProperty("teacher.student.added");
	}

	@Override
	public String updateMarks(Integer id, HashMap<String, Integer> subjectsMarks) throws TeacherExceptions {
		Gson gson = new Gson();
		try{
			template.put("http://StudentMS/student/updateMarks/"+id, gson.toJson(subjectsMarks));
		}catch(Exception e) {
			throw new TeacherExceptions(environment.getProperty("teacher.student.fail"));
		}
		return "Marks added successfully";
	}
	
	
}
