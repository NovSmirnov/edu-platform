package com.example.eduplatform.mappers;


import com.example.eduplatform.dto.CourseDto;
import com.example.eduplatform.entities.CourseEntity;

public class CourseMapper {

    private CourseMapper() {

    }

    public static CourseDto toDto(CourseEntity courseEntity) {
        return CourseDto.builder()
                .courseId(courseEntity.getCourseId())
                .courseName(courseEntity.getCourseName())
                .startDate(courseEntity.getStartDate())
                .finishDate(courseEntity.getFinishDate())
                .isActive(courseEntity.isActive())
                .build();
    }

    public static CourseEntity toEntity(CourseDto courseDto) {
        return CourseEntity.builder()
                .courseId(courseDto.getCourseId())
                .courseName(courseDto.getCourseName())
                .startDate(courseDto.getStartDate())
                .finishDate(courseDto.getFinishDate())
                .isActive(courseDto.isActive())
                .build();
    }
}
