package net.javaguideszain.employeeservice.service.implementation;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import net.javaguideszain.employeeservice.dto.ApiResponseDto;
import net.javaguideszain.employeeservice.dto.DepartmentDto;
import net.javaguideszain.employeeservice.dto.EmployeeDto;
import net.javaguideszain.employeeservice.entity.Employee;
import net.javaguideszain.employeeservice.mapper.EmployeeMapper;
import net.javaguideszain.employeeservice.repository.EmployeeRepository;
import net.javaguideszain.employeeservice.service.APIClient;
import net.javaguideszain.employeeservice.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImplementation implements EmployeeService {

    EmployeeRepository employeeRepository;
    //    RestTemplate restTemplate;
//    private APIClient apiClient;
    private WebClient webClient;

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

    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public ApiResponseDto findEmployeeById(Long empId) {

        Employee existingEmployee = employeeRepository.findById(empId).orElseThrow(
                () -> new RuntimeException("Employee not found")
        );

        /*FOR REST TEMPLATE */
//        ResponseEntity<DepartmentDto> departmentDtoResponseEntity = restTemplate.getForEntity("http://localhost:9091/api/v1/department/" + existingEmployee.getDepartmentCode(),
//                DepartmentDto.class);
//        DepartmentDto departmentDto = departmentDtoResponseEntity.getBody();
        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(existingEmployee);

         /*FOR WEBCLIENT*/
       DepartmentDto departmentDto = webClient.get()
                .uri("http://localhost:9091/api/v1/department/" + existingEmployee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();

        /*FOR OPENFEIGN LIBRARY*/
//        DepartmentDto departmentDto = apiClient.getDepartmentByCode(existingEmployee.getDepartmentCode());

        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);
        return apiResponseDto;
    }

    public ApiResponseDto getDefaultDepartment(Long empId, Exception e) {

        Employee existingEmployee = employeeRepository.findById(empId).orElseThrow(
                () -> new RuntimeException("Employee not found")
        );

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("RSD Department");
        departmentDto.setDepartmentCode("RSD001 Department");
        departmentDto.setDepartmentDescription("Research and Development Department");
        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(existingEmployee);

        ApiResponseDto apiResponseDto = new ApiResponseDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);
        return apiResponseDto;
    }
}
