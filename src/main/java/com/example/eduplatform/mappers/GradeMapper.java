package com.example.eduplatform.mappers;

import com.example.eduplatform.dto.GradeDto;
import com.example.eduplatform.entities.GradeEntity;

public class GradeMapper {

    private GradeMapper() {

    }

    public static GradeDto toDto (GradeEntity gradeEntity) {
        return GradeDto.builder()
                .gradeId(gradeEntity.getGradeId())
                .student(StudentMapper.toDto(gradeEntity.getStudent()))
                .subject(SubjectMapper.toDto(gradeEntity.getSubject()))
                .grade(gradeEntity.getGrade())
                .build();
    }

    public static GradeEntity toEntity(GradeDto gradeDto) {
        return GradeEntity.builder()
                .gradeId(gradeDto.getGradeId())
                .student(StudentMapper.toEntity(gradeDto.getStudent()))
                .subject(SubjectMapper.toEntity(gradeDto.getSubject()))
                .grade(gradeDto.getGrade())
                .build();
    }
}
