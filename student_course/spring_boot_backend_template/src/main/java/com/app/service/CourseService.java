package com.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.dao.CourseRepository;
import com.app.model.Course;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public Course addCourse(Course course) {
        // add validation logic if needed
        return courseRepository.save(course);
    }

    public ResponseEntity<String> updateCourseFees(Long courseId, double fees) {
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            course.setFees(fees);
            courseRepository.save(course);
            return ResponseEntity.ok("Course fees updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
        }
    }
}
