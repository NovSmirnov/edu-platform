package com.example.eduplatform.controllers;


import com.example.eduplatform.dto.StudentDto;
import com.example.eduplatform.entities.StudentEntity;
import com.example.eduplatform.mappers.StudentMapper;
import com.example.eduplatform.repositories.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/platform")
public class StudentController {

    private final StudentRepo studentRepo;

    @Autowired
    public StudentController(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

//    @PostMapping("/student/1")
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
//    public StudentDto getStudent(@RequestBody String id) {
//        StudentEntity studentEntity = studentRepo.findById(Long.parseLong(id)).orElse(new StudentEntity());
//        System.out.println(StudentMapper.toDto(studentEntity));
//        return StudentMapper.toDto(studentEntity);
//    }



    @PostMapping("/student")
    @ResponseStatus(HttpStatus.OK)
    public void setStudent(@RequestBody StudentDto student) {
        StudentEntity studentEntity = StudentMapper.toEntity(student);
        studentRepo.save(studentEntity);
    }

    @PutMapping("/student")
    @ResponseStatus(HttpStatus.OK)
    public void changeStudent(@RequestBody StudentDto student) {
        Long studentId = student.getStudentId();
        StudentEntity studentEntity = studentRepo.findById(studentId).orElse(new StudentEntity());
        if (studentEntity.getFirstName() != null) {
            studentEntity = StudentMapper.toEntity(student);
            studentRepo.save(studentEntity);
        }
    }
}
