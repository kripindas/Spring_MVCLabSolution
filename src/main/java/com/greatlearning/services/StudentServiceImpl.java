package com.greatlearning.services;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.greatlearning.model.Student;

@Repository
public class StudentServiceImpl implements IStudentService {

	private SessionFactory sessionFactory;
	private Session session;

	@Autowired
	public StudentServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		session = this.sessionFactory.openSession();
	}

	@Override
	@Transactional
	public void saveStudent(Student student) {
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(student);
		transaction.commit();
	}

	@Override
	@Transactional
	public Student findById(int id) {
		Transaction transaction = session.beginTransaction();
		Student student = session.get(Student.class, id);
		transaction.commit();
		return student;
	}

	@Override
	@Transactional
	public void deleteStudent(int id) {
		Transaction transaction = session.beginTransaction();
		Student deleteRecord = session.get(Student.class, id);
		if (deleteRecord != null) {
			session.delete(deleteRecord);
		}
		transaction.commit();
	}

	@Override
	@Transactional
	public List<Student> getAllStudents() {
		Transaction transaction = session.beginTransaction();
		List<Student> studentRecords = session.createQuery("from Student").list();
		transaction.commit();
		return studentRecords;
	}

}
