package com.example.eduplatform.controllers;

import com.example.eduplatform.dto.GradeDto;
import com.example.eduplatform.dto.StudentDto;
import com.example.eduplatform.entities.*;
import com.example.eduplatform.mappers.GradeMapper;
import com.example.eduplatform.mappers.StudentMapper;
import com.example.eduplatform.repositories.*;
import com.example.eduplatform.structures.StudentsRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@RestController
@RequestMapping("/platform")
public class GradeController {

    private final GradeRepo gradeRepo;
    private final CourseRepo courseRepo;
    private final StudentRepo studentRepo;
    private final SubjectRepo subjectRepo;
    private final CourseStudentRepo courseStudentRepo;

    @Autowired
    public GradeController(GradeRepo gradeRepo, CourseRepo courseRepo, StudentRepo studentRepo,
                           SubjectRepo subjectRepo, CourseStudentRepo courseStudentRepo) {
        this.gradeRepo = gradeRepo;
        this.courseRepo = courseRepo;
        this.studentRepo = studentRepo;
        this.subjectRepo = subjectRepo;
        this.courseStudentRepo = courseStudentRepo;
    }


//    @PostMapping("/grades/1")
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
//    public GradeDto getCourse(@RequestBody String id) {
//        GradeEntity gradeEntity = gradeRepo.findById(Long.parseLong(id)).orElse(GradeEntity.builder()
//                .student(new StudentEntity())
//                .subject(new SubjectEntity())
//                .build());
//        System.out.println(GradeMapper.toDto(gradeEntity));
//        return GradeMapper.toDto(gradeEntity);
//    }


    @PostMapping("/grades")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public StudentsRating getAverageGrades(@RequestBody StudentDto student) {
        StudentEntity studentEntity = studentRepo.findById(student.getStudentId()).orElse(new StudentEntity());
        List<CourseStudentEntity> courseStudentEntities = courseStudentRepo.findAllByStudentEntity(studentEntity);
        List<GradeEntity> gradeEntities = gradeRepo.findAllByStudent(studentEntity);
        List<GradeEntity> actualGrades = gradeEntities //Находим оценки по предметам, где дата раньше текущей
                .stream()
                .filter(x -> x.getSubject().getDate().compareTo(new GregorianCalendar()) < 0)
                .toList();

        List<Long> courseIds = new ArrayList<>();
        List<String> courseNames = new ArrayList<>();

        for (CourseStudentEntity entity : courseStudentEntities) {
            courseIds.add(entity.getCourseEntity().getCourseId());
            courseNames.add(entity.getCourseEntity().getCourseName());
        }

        List<Double> maxGradesSumForCourse = new ArrayList<>(courseIds.size()); //Максимальные оценки в разрезе курсов
        List<Double> gradesSumForCourse = new ArrayList<>(courseIds.size()); //Полученные оценки в разрезе курсов
        List<Double> averageGradeForCourse = new ArrayList<>(courseIds.size()); //Средняя оценка по всем занятиям для каждого курса

        for (int i = 0; i < courseIds.size(); i++) {
            Double maxGradeSum = 0.0;
            Double gradeSum = 0.0;
            for (GradeEntity grade : actualGrades) {
                Long gradeCourseId = grade.getSubject().getCourse().getCourseId();
                if (gradeCourseId.equals(courseIds.get(i))) {
                    maxGradeSum += grade.getSubject().getMaxGrade();
                    gradeSum += grade.getGrade();
                }
            }
            maxGradesSumForCourse.add(maxGradeSum);
            gradesSumForCourse.add(gradeSum);
        }
        for (int i = 0; i < courseIds.size(); i++) {
            averageGradeForCourse.add(gradesSumForCourse.get(i) / maxGradesSumForCourse.get(i) * 100);
        }
        return new StudentsRating(StudentMapper.toDto(studentEntity), courseNames, averageGradeForCourse);
    }
}
