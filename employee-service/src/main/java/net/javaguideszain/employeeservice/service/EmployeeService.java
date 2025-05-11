package net.javaguideszain.employeeservice.service;

import net.javaguideszain.employeeservice.dto.ApiResponseDto;
import net.javaguideszain.employeeservice.dto.EmployeeDto;

public interface EmployeeService {

    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    EmployeeDto findByEmployeeEmail(String email);

    ApiResponseDto findEmployeeById(Long empId);
}
