package net.javaguideszain.departmentservice.controller;

import lombok.AllArgsConstructor;
import net.javaguideszain.departmentservice.dto.DepartmentDto;
import net.javaguideszain.departmentservice.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/department")
@AllArgsConstructor
public class DepartmentController {

    DepartmentService departmentService;
    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto savedDepartment = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @GetMapping("{code}")
    public ResponseEntity<DepartmentDto> getDepartmentByCode(@PathVariable("code") String depcode){
         return ResponseEntity.ok(departmentService.findByDepartmentCode(depcode));
    }
}
