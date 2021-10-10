package com.saraya.repository;

import com.saraya.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // CUSTOM QUERY
    @Query(value = "select * from employee e where e.name like %:keyword% or e.email like %:keyword% or e.phone like %:keyword%", nativeQuery = true)
    List<Employee> findByKeyword(@Param("keyword") String keyword);

    Optional<Employee> findByEmailContaining(String email);
    Optional<Employee> findByPhoneContaining(String phone);

    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);

}
