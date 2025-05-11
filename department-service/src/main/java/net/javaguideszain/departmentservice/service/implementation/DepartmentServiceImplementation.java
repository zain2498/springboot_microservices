package net.javaguideszain.departmentservice.service.implementation;

import lombok.AllArgsConstructor;
import net.javaguideszain.departmentservice.dto.DepartmentDto;
import net.javaguideszain.departmentservice.entity.Department;
import net.javaguideszain.departmentservice.mapper.DepartmentMapper;
import net.javaguideszain.departmentservice.repository.DepartmentRepository;
import net.javaguideszain.departmentservice.service.DepartmentService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImplementation implements DepartmentService {

    DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department department = DepartmentMapper.mapToDepartment(departmentDto);
        Department savedDepartment = departmentRepository.save(department);
        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }

    @Override
    public DepartmentDto findByDepartmentCode(String departmentCode) {
        Department existingDepartment = departmentRepository.findByDepartmentCode(departmentCode).orElseThrow(
                () -> new RuntimeException("Department is not found")
        );
        return DepartmentMapper.mapToDepartmentDto(existingDepartment);
    }
}
