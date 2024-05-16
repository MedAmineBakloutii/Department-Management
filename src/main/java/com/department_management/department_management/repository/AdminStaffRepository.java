package com.department_management.department_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.department_management.department_management.model.AdminStaff;

import java.util.Optional;

@Repository
public interface AdminStaffRepository extends JpaRepository<AdminStaff, Long> {
    Optional<AdminStaff> findByEmailAndPassword(String email, String password);
}
