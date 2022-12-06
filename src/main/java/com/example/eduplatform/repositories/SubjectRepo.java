package com.example.eduplatform.repositories;

import com.example.eduplatform.entities.CourseEntity;
import com.example.eduplatform.entities.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface SubjectRepo extends JpaRepository<SubjectEntity, Long> {
    Set<SubjectEntity> findAllByCourse(CourseEntity courseEntity);

}
