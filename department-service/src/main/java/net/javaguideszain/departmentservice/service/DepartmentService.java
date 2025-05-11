package net.javaguideszain.departmentservice.service;

import net.javaguideszain.departmentservice.dto.DepartmentDto;

public interface DepartmentService {

    DepartmentDto saveDepartment(DepartmentDto departmentDto);

    DepartmentDto findByDepartmentCode(String departmentCode);
}
