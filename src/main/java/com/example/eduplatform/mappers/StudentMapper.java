package com.example.eduplatform.mappers;

import com.example.eduplatform.dto.StudentDto;
import com.example.eduplatform.entities.StudentEntity;

public class StudentMapper {

    private StudentMapper() {

    }

    public static StudentDto toDto (StudentEntity studentEntity) {
        return StudentDto.builder()
                .studentId(studentEntity.getStudentId())
                .firstName(studentEntity.getFirstName())
                .lastName(studentEntity.getLastName())
                .patronymic(studentEntity.getPatronymic())
                .groupNumber(studentEntity.getGroupNumber())
                .isActive(studentEntity.isActive())
                .build();
    }
    public static StudentEntity toEntity(StudentDto studentDto) {
        return StudentEntity.builder()
                .studentId(studentDto.getStudentId())
                .firstName(studentDto.getFirstName())
                .lastName(studentDto.getLastName())
                .patronymic(studentDto.getPatronymic())
                .groupNumber(studentDto.getGroupNumber())
                .isActive(studentDto.isActive())
                .build();
    }
}
