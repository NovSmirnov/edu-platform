package com.example.eduplatform.controllers;

import com.example.eduplatform.dto.CourseDto;
import com.example.eduplatform.entities.CourseEntity;
import com.example.eduplatform.mappers.CourseMapper;
import com.example.eduplatform.repositories.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/platform")
public class CourseController {

    private final CourseRepo courseRepo;

    @Autowired
    public CourseController(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

//    @PostMapping("/course/1")
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
//    public CourseDto getCourse(@RequestBody String id) {
//        CourseEntity courseEntity = courseRepo.findById(Long.parseLong(id)).orElse(new CourseEntity());
//        System.out.println(CourseMapper.toDto(courseEntity));
//        return CourseMapper.toDto(courseEntity);
//    }


    @PostMapping("/course")
    @ResponseStatus(HttpStatus.OK)
    public void setCourse(@RequestBody CourseDto course) {
        CourseEntity courseEntity = CourseMapper.toEntity(course);
        courseRepo.save(courseEntity);
    }

    @PutMapping("course/")
    @ResponseStatus(HttpStatus.OK)
    public void changeCourse(@RequestBody CourseDto course) {
        Long courseId = course.getCourseId();
        CourseEntity courseEntity = courseRepo.findById(courseId).orElse(new CourseEntity());
        if (courseEntity.getCourseName() != null) {
            courseEntity = CourseMapper.toEntity(course);
            courseRepo.save(courseEntity);
        }
    }



}
