package net.javaguideszain.departmentservice.mapper;

import net.javaguideszain.departmentservice.dto.DepartmentDto;
import net.javaguideszain.departmentservice.entity.Department;

public class DepartmentMapper {

    public static Department mapToDepartment(DepartmentDto departmentDto){
        return  new Department(
                departmentDto.getDepartmentId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription(),
                departmentDto.getDepartmentCode()
        );
    }
    public static DepartmentDto mapToDepartmentDto(Department department){
        return  new DepartmentDto(
                department.getDepartmentId(),
                department.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getDepartmentCode()
        );
    }
}
