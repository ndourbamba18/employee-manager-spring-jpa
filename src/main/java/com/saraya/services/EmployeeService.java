package com.saraya.services;

import com.saraya.entities.Employee;
import com.saraya.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Page<Employee> list(Pageable pageable){
        return employeeRepository.findAll(pageable);
    }

    public Optional<Employee> getOne(int id){
        return employeeRepository.findById(id);
    }

    public Optional<Employee> getEmail(String email){
        return employeeRepository.findByEmailContaining(email);
    }

    public Optional<Employee> getPhone(String phone){
        return employeeRepository.findByPhoneContaining(phone);
    }

    public void saveEmployee(Employee employee){
        this.employeeRepository.save(employee);
    }

    public void deleteEmployee(int id){
        Employee employee = employeeRepository.findById(id).get();
        employeeRepository.delete(employee);
    }

    public Boolean existsById(int id){
        return employeeRepository.existsById(id);
    }

    public Boolean existsByEmail(String email){
        return employeeRepository.existsByEmail(email);
    }

    public Boolean existsByPhone(String phone){
        return employeeRepository.existsByPhone(phone);
    }

    public List<Employee> searchEmployeeByKeyword(String keyword){
        return employeeRepository.findByKeyword(keyword);
    }
}
