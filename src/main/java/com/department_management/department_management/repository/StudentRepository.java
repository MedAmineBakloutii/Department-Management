package com.department_management.department_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.department_management.department_management.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
