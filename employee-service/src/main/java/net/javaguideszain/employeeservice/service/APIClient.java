package net.javaguideszain.employeeservice.service;

import net.javaguideszain.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface APIClient {
    @GetMapping("api/v1/department/{code}")
    public DepartmentDto getDepartmentByCode(@PathVariable("code") String depcode);
}
