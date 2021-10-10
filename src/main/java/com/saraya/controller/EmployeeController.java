package com.saraya.controller;

import com.saraya.dto.EmployeeDto;
import com.saraya.entities.Employee;
import com.saraya.message.Message;
import com.saraya.services.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1")
@CrossOrigin(origins = "*")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /*
     * GETTING ALL EMPLOYEES
     * URL : http://127.0.0.1:8080/api/v1/employees
     */
    @GetMapping(path = "/employees")
    public ResponseEntity<?> getAllEmployees(Pageable pageable){
        Page<Employee> employees = employeeService.list(pageable);
        if (employees.isEmpty())
            return new ResponseEntity<>(new Message("List of employees is empty!"), HttpStatus.BAD_GATEWAY);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    
    /*
     * SEARCH ALL EMPLOYEES FROM DATABASE
     * URL : http://127.0.0.1:8080/api/v1/employees/search/{keyword}
     */
    @GetMapping(path = "/employees/search/{keyword}")
    public ResponseEntity<?> searchEmployee(@PathVariable String keyword){
        List<Employee> employees = employeeService.searchEmployeeByKeyword(keyword);
        if (employees.isEmpty())
            return new ResponseEntity<>(new Message("Sorry, there are no content!"), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    /*
     * GETTING A SINGLE EMPLOYEE BY ID
     * URL : http://127.0.0.1:8080/api/v1/employees/detail/{id}
     */
    @GetMapping(path = "/employees/detail/{employeeId}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("employeeId") Integer employeeId){
       try {
           if (!employeeService.existsById(employeeId))
               return new ResponseEntity<>(new Message("Employee does not exist : "+employeeId), HttpStatus.BAD_REQUEST);
           Optional<Employee> employee = employeeService.getOne(employeeId);
           return new ResponseEntity<>(employee, HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<>(new Message("ERROR REQUEST :("), HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    /*
     * GETTING A SINGLE EMPLOYEE BY EMAIL
     * URL : http://127.0.0.1:8080/api/v1/employees/detail-email/{email}
     */
    @GetMapping(path = "/employees/detail-email/{employeeEmail}")
    public ResponseEntity<?> getEmployeeByEmail(@PathVariable String employeeEmail){
        if (!employeeService.existsByEmail(employeeEmail))
            return new ResponseEntity<>(new Message("Employee does not exist : "+employeeEmail), HttpStatus.BAD_REQUEST);
        Employee employee = employeeService.getEmail(employeeEmail).get();
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    /*
     * GETTING A SINGLE EMPLOYEE BY PHONE NUMBER
     * URL : http://127.0.0.1:8080/api/v1/employees/detail-phone/{phone}
     */
    @GetMapping(path = "/employees/detail-phone/{employeePhone}")
    public ResponseEntity<?> getEmployeeByPhoneNumber(@PathVariable String employeePhone){
        if (!employeeService.existsByPhone(employeePhone))
            return new ResponseEntity<>(new Message("Employee does not exist : "+employeePhone), HttpStatus.BAD_REQUEST);
        Employee employee = employeeService.getEmail(employeePhone).get();
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    /*
     * DELETE A EMPLOYEE BY ID FROM DATABASE
     * URL : http://127.0.0.1:8080/api/v1/employees/{id}
     */
    @DeleteMapping(path = "/employees/{employeeId}")
    public  ResponseEntity<?> deleteEmployee(@PathVariable int employeeId){
        if (!employeeService.existsById(employeeId))
            return new ResponseEntity<>(new Message("Employee does not exist : "+employeeId), HttpStatus.BAD_REQUEST);
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>(new Message("Employee has bean deleted successfully : "+employeeId), HttpStatus.OK);
    }
    
    /*
     * POST A NEW EMPLOYEE FROM DATABASE
     * URL : http://127.0.0.1:8080/api/v1/employees
     */
    @PostMapping(path = "/employees")
    public ResponseEntity<?> saveNewEmployee(@Valid @RequestBody EmployeeDto employeeDto){
        if (employeeService.existsByEmail(employeeDto.getEmail()))
            return new ResponseEntity<>(new Message("Employee email already exist!"), HttpStatus.BAD_REQUEST);
        if (employeeService.existsByPhone(employeeDto.getPhone()))
            return new ResponseEntity<>(new Message("Employee phone number already exist!"), HttpStatus.BAD_REQUEST);

        Employee employee = new Employee(employeeDto.getName(), employeeDto.getEmail(), employeeDto.getPhone(), employeeDto.getDepartment(),
                                                  employeeDto.getBaseLocation(), employeeDto.getAddress());
        employeeService.saveEmployee(employee);
        return new ResponseEntity<>(new Message("Employee has bean created successfully : "+employee.getName()), HttpStatus.OK);
    }

    /*
     * UPDATE A EMPLOYEE BY ID FROM DATABASE
     * URL : http://127.0.0.1:8080/api/v1/employees/{id}
     */
    @PutMapping(path = "/employees/{employeeId}")
    public ResponseEntity<?> updateEmployeeById(@Valid @RequestBody EmployeeDto employeeDto, @PathVariable int employeeId){
        if (!employeeService.existsById(employeeId))
            return new ResponseEntity<>(new Message("Employee does not exist : "+employeeId), HttpStatus.BAD_REQUEST);
        if (employeeService.existsByEmail(employeeDto.getEmail()) && employeeService.getEmail(employeeDto.getEmail()).get().getId() != employeeId)
            return new ResponseEntity<>(new Message("Employee email already exist!"), HttpStatus.BAD_REQUEST);
        if (employeeService.existsByPhone(employeeDto.getPhone()) && employeeService.getPhone(employeeDto.getPhone()).get().getId() != employeeId)
            return new ResponseEntity<>(new Message("Employee phone number already exist!"), HttpStatus.BAD_REQUEST);

        Employee employee = new Employee(employeeDto.getName(), employeeDto.getEmail(), employeeDto.getPhone(), employeeDto.getDepartment(), employeeDto.getBaseLocation(), employeeDto.getAddress());
        employee.setName(employeeDto.getName());
        employee.setEmail(employeeDto.getEmail());
        employee.setPhone(employeeDto.getPhone());
        employee.setDepartment(employeeDto.getDepartment());
        employee.setBaseLocation(employeeDto.getBaseLocation());
        employeeService.saveEmployee(employee);
        return new ResponseEntity<>(new Message("Employee has bean updated successfully : "+employeeId), HttpStatus.OK);
    }

}
