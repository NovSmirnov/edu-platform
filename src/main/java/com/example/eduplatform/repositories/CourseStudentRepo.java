package com.example.eduplatform.repositories;

import com.example.eduplatform.entities.CourseStudentEntity;
import com.example.eduplatform.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseStudentRepo extends JpaRepository<CourseStudentEntity, Long> {

    List<CourseStudentEntity> findAllByStudentEntity(StudentEntity studentId);
}
