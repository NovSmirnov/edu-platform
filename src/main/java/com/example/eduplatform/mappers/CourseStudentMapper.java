package com.example.eduplatform.mappers;

import com.example.eduplatform.dto.CourseStudentDto;
import com.example.eduplatform.entities.CourseStudentEntity;
import com.example.eduplatform.repositories.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class CourseStudentMapper {
    public CourseStudentMapper(CourseRepo courseRepo) {
    }

    public static CourseStudentDto toDto(CourseStudentEntity courseStudentEntity) {
        return CourseStudentDto.builder()
                .courseStudentId(courseStudentEntity.getCourseStudentId())
                .courseDto(CourseMapper.toDto(courseStudentEntity.getCourseEntity()))
                .studentDto(StudentMapper.toDto(courseStudentEntity.getStudentEntity()))
                .date(courseStudentEntity.getDate())
                .isActive(courseStudentEntity.isActive())
                .build();
    }

    public static CourseStudentEntity toEntity(CourseStudentDto courseStudentDto) {
        return CourseStudentEntity.builder()
                .courseStudentId(courseStudentDto.getCourseStudentId())
                .courseEntity(CourseMapper.toEntity(courseStudentDto.getCourseDto()))
                .studentEntity(StudentMapper.toEntity(courseStudentDto.getStudentDto()))
                .date(courseStudentDto.getDate())
                .isActive(courseStudentDto.isActive())
                .build();
    }
}
