package com.example.EmployeeManagementSystem.service;

import com.example.EmployeeManagementSystem.dto.EmployeeDto;
import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.exception.ResourceNotFoundException;
import com.example.EmployeeManagementSystem.mapper.EmployeeMapper;
import com.example.EmployeeManagementSystem.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService implements EmployeeServiceImp{

    @Autowired
    EmployeeRepository employeeRepository;


    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee =employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(savedEmployee);

    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {

        Employee employee=employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee not exists with given id" + employeeId));

        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    public  List<EmployeeDto> getAllEmployee(){

        List<Employee> allEmployee=employeeRepository.findAll();
        return allEmployee.stream().map(EmployeeMapper::mapToEmployeeDto).toList();
    }

    public EmployeeDto getEmployeeSalary(Long id) {

        Employee employeeSalary=employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not exists with given id" + id));
        return EmployeeMapper.mapToEmployeeDto(employeeSalary);
    }
}
