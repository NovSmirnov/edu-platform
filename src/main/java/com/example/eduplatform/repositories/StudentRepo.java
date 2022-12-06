package com.example.eduplatform.repositories;

import com.example.eduplatform.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<StudentEntity, Long> {

}
