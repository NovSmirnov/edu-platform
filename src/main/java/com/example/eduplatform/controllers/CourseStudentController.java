package com.example.eduplatform.controllers;

import com.example.eduplatform.dto.CourseStudentDto;
import com.example.eduplatform.entities.*;
import com.example.eduplatform.mappers.CourseStudentMapper;
import com.example.eduplatform.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/platform")
public class CourseStudentController {

    private final CourseStudentRepo courseStudentRepo;
    private final GradeRepo gradeRepo;
    private final SubjectRepo subjectRepo;
    private final CourseRepo courseRepo;
    private final StudentRepo studentRepo;

    @Autowired
    public CourseStudentController(CourseStudentRepo courseStudentRepo, GradeRepo gradeRepo, SubjectRepo subjectRepo,
                                   CourseRepo courseRepo, StudentRepo studentRepo) {
        this.courseStudentRepo = courseStudentRepo;
        this.gradeRepo = gradeRepo;
        this.subjectRepo = subjectRepo;
        this.courseRepo = courseRepo;
        this.studentRepo = studentRepo;
    }

//    @PostMapping("/course_student/1")
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
//    public CourseStudentDto getCourseStudent(@RequestBody String id) {
//        CourseStudentEntity courseStudentEntity = courseStudentRepo.findById(Long.parseLong(id)).orElse(CourseStudentEntity.builder()
//                .courseEntity(new CourseEntity())
//                .studentEntity(new StudentEntity())
//                .build());
//        System.out.println(CourseStudentMapper.toDto(courseStudentEntity));
//        return CourseStudentMapper.toDto(courseStudentEntity);
//    }


    @PostMapping("/course_student")
    @ResponseStatus(HttpStatus.OK)
    public void setStudentCourse(@RequestBody CourseStudentDto courseStudent) {
        CourseEntity courseEntity = courseRepo.findById(courseStudent.getCourseDto().getCourseId()).orElse(new CourseEntity());
        StudentEntity studentEntity = studentRepo.findById(courseStudent.getStudentDto().getStudentId()).orElse(new StudentEntity());
        CourseStudentEntity courseStudentEntity = CourseStudentMapper.toEntity(courseStudent);
        courseStudentEntity.setCourseEntity(courseEntity);
        courseStudentEntity.setStudentEntity(studentEntity);
        CourseStudentEntity createdCourseStudentEntity = courseStudentRepo.save(courseStudentEntity);

        //Создаём сущности для таблицы с оценками. Для каждого занятия студента своя сущность. Оценки будут выставляться позже
        Set<SubjectEntity> subjectSet = subjectRepo.findAllByCourse(courseEntity);
        Iterator<SubjectEntity> iterator = subjectSet.iterator();
        List<GradeEntity> gradeList = new ArrayList<>();
        while (iterator.hasNext()) {
            GradeEntity gradeEntity = GradeEntity.builder()
                    .student(studentEntity)
                    .subject(iterator.next())
                    .build();
            gradeList.add(gradeEntity);
        }
        gradeRepo.saveAll(gradeList);
    }

    @PutMapping("/course_student")
    @ResponseStatus(HttpStatus.OK)
    public void changeStudentCourse(@RequestBody CourseStudentDto courseStudent) {
        Long courseStudentId = courseStudent.getCourseStudentId();
        CourseStudentEntity courseStudentEntity = courseStudentRepo.findById(courseStudentId).orElse(new CourseStudentEntity());
        if (courseStudentEntity.getCourseStudentId() != null) {
            courseStudentEntity = CourseStudentMapper.toEntity(courseStudent);
            courseStudentRepo.save(courseStudentEntity);
        }
    }
}
