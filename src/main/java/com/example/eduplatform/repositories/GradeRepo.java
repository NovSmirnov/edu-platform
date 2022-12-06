package com.example.eduplatform.repositories;

import com.example.eduplatform.entities.GradeEntity;
import com.example.eduplatform.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepo extends JpaRepository<GradeEntity, Long> {
    List<GradeEntity> findAllByStudent(StudentEntity studentEntity);
}
