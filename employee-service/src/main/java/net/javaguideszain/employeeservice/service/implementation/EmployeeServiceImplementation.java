package net.javaguideszain.employeeservice.service.implementation;

import lombok.AllArgsConstructor;
import net.javaguideszain.employeeservice.dto.ApiResponseDto;
import net.javaguideszain.employeeservice.dto.DepartmentDto;
import net.javaguideszain.employeeservice.dto.EmployeeDto;
import net.javaguideszain.employeeservice.entity.Employee;
import net.javaguideszain.employeeservice.mapper.EmployeeMapper;
import net.javaguideszain.employeeservice.repository.EmployeeRepository;
import net.javaguideszain.employeeservice.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class EmployeeServiceImplementation implements EmployeeService {

    EmployeeRepository employeeRepository;
    RestTemplate restTemplate;


    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto findByEmployeeEmail(String email) {
        Employee existingEmployee = employeeRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Employee not found")
        );
        return EmployeeMapper.mapToEmployeeDto(existingEmployee);
    }

    @Override
    public ApiResponseDto findEmployeeById(Long empId) {

        Employee existingEmployee = employeeRepository.findById(empId).orElseThrow(
                () -> new RuntimeException("Employee not found")
        );

        ResponseEntity<DepartmentDto> departmentDtoResponseEntity = restTemplate.getForEntity("http://localhost:9091/api/v1/department/" + existingEmployee.getDepartmentCode(),
                DepartmentDto.class);
        DepartmentDto departmentDto = departmentDtoResponseEntity.getBody();
        EmployeeDto employeeDto= EmployeeMapper.mapToEmployeeDto(existingEmployee);

        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);
        return apiResponseDto;
    }
}
