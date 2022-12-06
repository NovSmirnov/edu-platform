package com.example.eduplatform.controllers;

import com.example.eduplatform.dto.SubjectDto;
import com.example.eduplatform.entities.CourseEntity;
import com.example.eduplatform.entities.SubjectEntity;
import com.example.eduplatform.mappers.SubjectMapper;
import com.example.eduplatform.repositories.CourseRepo;
import com.example.eduplatform.repositories.StudentRepo;
import com.example.eduplatform.repositories.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/platform")
public class SubjectController {

    private final SubjectRepo subjectRepo;
    private final CourseRepo courseRepo;
    private final StudentRepo studentRepo;

    @Autowired
    public SubjectController(SubjectRepo subjectRepo, CourseRepo courseRepo , StudentRepo studentRepo) {
        this.subjectRepo = subjectRepo;
        this.courseRepo = courseRepo;
        this.studentRepo = studentRepo;
    }

//    @PostMapping("/subject/1")
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
//    public SubjectDto getCourse(@RequestBody Long id) {
//        SubjectEntity subjectEntity = subjectRepo.findById(id).orElse(new SubjectEntity());
//        System.out.println(SubjectMapper.toDto(subjectEntity));
//        return SubjectMapper.toDto(subjectEntity);
//    }




    @PostMapping("/subject")
    @ResponseStatus(HttpStatus.OK)
    public void setSubject(@RequestBody SubjectDto subject) {
        Long courseId = Long.parseLong(String.valueOf(subject.getCourse().getCourseId()));
        CourseEntity courseEntity = courseRepo.findById(courseId).orElse(new CourseEntity());
        SubjectEntity subjectEntity = SubjectMapper.toEntity(subject);
        subjectEntity.setCourse(courseEntity);
        subjectRepo.save(subjectEntity);
    }

    @PutMapping("/subject")
    @ResponseStatus(HttpStatus.OK)
    public void changeSubject(@RequestBody SubjectDto subjectDto) {
        Long subjectId = subjectDto.getSubjectId();
        SubjectEntity subjectEntity = subjectRepo.findById(subjectId).orElse(new SubjectEntity());
        if (subjectEntity.getSubjectName() != null) {
            subjectEntity = SubjectMapper.toEntity(subjectDto);
            subjectRepo.save(subjectEntity);
        }
    }

}
