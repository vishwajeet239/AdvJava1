package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByCourse_Title(String courseTitle);
}
