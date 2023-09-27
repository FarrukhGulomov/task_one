package uz.pdp.task_one.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task_one.exception.ExceptionMessage;
import uz.pdp.task_one.payload.DepartmentDto;
import uz.pdp.task_one.service.DepartmentService;

@RestController
@Validated
@RequestMapping("/api/department")
public class DepartmentController extends ExceptionMessage {

    DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<?> addDepartment(@Valid @RequestBody DepartmentDto departmentDto){
        return departmentService.addDepartment(departmentDto);
    }

    @GetMapping
    public ResponseEntity<?> getDepartmentsByPage(@RequestParam Integer page){
        return departmentService.getDepartmentsByPage(page);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartment(@PathVariable Long id){
        return departmentService.getDepartment(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editDepartment(@PathVariable Long id, @Valid @RequestBody DepartmentDto departmentDto){
        return departmentService.editDepartment(id,departmentDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Long id){
        return departmentService.deleteDepartment(id);
    }


}
