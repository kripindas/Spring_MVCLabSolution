package com.greatlearning.services;

import java.util.List;

import com.greatlearning.model.Student;


public interface IStudentService {
	
	public void saveStudent(Student student);
	
	public Student findById(int id);
	
	public void deleteStudent(int id);
	
	public List<Student> getAllStudents();

}
