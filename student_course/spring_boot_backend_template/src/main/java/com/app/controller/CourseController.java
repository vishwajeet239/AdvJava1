package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Course;
import com.app.service.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<String> launchNewCourse(@RequestBody Course course) {
        Course savedCourse = courseService.addCourse(course);
        return ResponseEntity.ok("Course launched successfully with ID: " + savedCourse.getId());
    }

    @PutMapping("/{courseId}/fees/{fees}")
    public ResponseEntity<String> updateCourseFees(@PathVariable Long courseId, @PathVariable double fees) {
        ResponseEntity<String> response = courseService.updateCourseFees(courseId, fees);
        return response;
    }
}
