package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.dao.CourseRepository;
import com.app.dao.StudentRepository;
import com.app.model.Course;
import com.app.model.Student;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public ResponseEntity<String> addStudent(Student student, Long courseId) {
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            if (student.getScore() >= course.getMinScore()) {
                student.setCourse(course);
                studentRepository.save(student);
                return ResponseEntity.ok("Student added successfully");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Admission denied. Score is below minimum required");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
        }
    }

    public List<Student> getStudentsByCourseTitle(String courseTitle) {
        return studentRepository.findByCourse_Title(courseTitle);
    }

    public ResponseEntity<String> cancelAdmission(Long courseId, Long studentId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            if (student.getCourse().getId().equals(courseId)) {
                studentRepository.delete(student);
                return ResponseEntity.ok("Student admission canceled successfully");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Student is not enrolled in the specified course");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
    }
}
