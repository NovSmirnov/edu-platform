package com.example.eduplatform.mappers;

import com.example.eduplatform.dto.SubjectDto;
import com.example.eduplatform.entities.SubjectEntity;

public class SubjectMapper {

    private SubjectMapper() {

    }

    public static SubjectDto toDto(SubjectEntity subjectEntity) {
        return SubjectDto.builder()
                .subjectId(subjectEntity.getSubjectId())
                .subjectName(subjectEntity.getSubjectName())
                .course(CourseMapper.toDto(subjectEntity.getCourse()))
                .date(subjectEntity.getDate())
                .maxGrade(subjectEntity.getMaxGrade())
                .build();
    }

    public static SubjectEntity toEntity(SubjectDto subjectDto) {
        return SubjectEntity.builder()
                .subjectId(subjectDto.getSubjectId())
                .subjectName(subjectDto.getSubjectName())
                .course(CourseMapper.toEntity(subjectDto.getCourse()))
                .date(subjectDto.getDate())
                .maxGrade(subjectDto.getMaxGrade())
                .build();
    }
}
