package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Student;
import com.app.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<String> addStudent(@RequestBody Student student, @RequestParam Long courseId) {
        ResponseEntity<String> response = studentService.addStudent(student, courseId);
        return response;
    }

    @GetMapping("/course_title/{courseTitle}")
    public ResponseEntity<List<Student>> viewStudentsByCourse(@PathVariable String courseTitle) {
        List<Student> students = studentService.getStudentsByCourseTitle(courseTitle);
        return ResponseEntity.ok(students);
    }

    @DeleteMapping("/{courseId}/students/{studentId}")
    public ResponseEntity<String> cancelStudentAdmission(@PathVariable Long courseId, @PathVariable Long studentId) {
        ResponseEntity<String> response = studentService.cancelAdmission(courseId, studentId);
        return response;
    }
}