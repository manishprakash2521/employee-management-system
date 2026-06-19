package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.dto.EmployeeDto;
import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<EmployeeDto> createEmployee(@Valid @RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee=employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee,HttpStatus.CREATED);
    }

    @GetMapping({"id"})
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long id){
        EmployeeDto employeeDto=employeeService.getEmployeeById(id);
        return  ResponseEntity.ok(employeeDto);
    }

    @GetMapping("/allEmployees")
    public ResponseEntity <List<EmployeeDto>> getAllEmployee(){
        List<EmployeeDto> allEmployeeDto=employeeService.getAllEmployee();
        return ResponseEntity.ok(allEmployeeDto);
    }

    @GetMapping("/{id}/salary")
    public ResponseEntity<EmployeeDto> getEmployeeSalary(@PathVariable("id") Long id){
        EmployeeDto employeeSalaryDto=employeeService.getEmployeeSalary(id);
        return ResponseEntity.ok(employeeSalaryDto);
    }
}
