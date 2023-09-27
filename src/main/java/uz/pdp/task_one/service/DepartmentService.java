package uz.pdp.task_one.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.task_one.entity.Company;
import uz.pdp.task_one.entity.Department;
import uz.pdp.task_one.payload.DepartmentDto;
import uz.pdp.task_one.repository.AddressRepository;
import uz.pdp.task_one.repository.CompanyRepository;
import uz.pdp.task_one.repository.DepartmentRepository;

import java.util.Optional;

@Service
public class DepartmentService {

    DepartmentRepository departmentRepository;
    CompanyRepository companyRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository, CompanyRepository companyRepository) {
        this.departmentRepository = departmentRepository;
        this.companyRepository = companyRepository;
    }


    // Add Department
    public ResponseEntity<?> addDepartment(DepartmentDto departmentDto) {
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (optionalCompany.isEmpty()) return ResponseEntity.status(409).body("Company is not found!");
        boolean existsDepartmentByNameAndCompanyId = departmentRepository.existsDepartmentByNameAndCompanyId(departmentDto.getName(), departmentDto.getCompanyId());

        if (existsDepartmentByNameAndCompanyId) return ResponseEntity.status(409).body("Department is already exist!");
        Department department = new Department();
        department.setCompany(optionalCompany.get());
        department.setName(departmentDto.getName());
        departmentRepository.save(department);
        return ResponseEntity.status(202).body("Department is successfully added!");
    }

    // Get Departments by Page
    public ResponseEntity<?> getDepartmentsByPage(Integer page) {
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        Page<Department> departments = departmentRepository.findAll(pageable);
        return ResponseEntity.status(202).body(departments);
    }

    // get department by id
    public ResponseEntity<?> getDepartment(Long id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isEmpty()) return ResponseEntity.status(409)
                .body("Department is not found!");

        return ResponseEntity.status(202).body(optionalDepartment.get());
    }

    // edit Department
    public ResponseEntity<?> editDepartment(Long id, DepartmentDto departmentDto) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isEmpty()) return ResponseEntity.status(409)
                .body("Department is not found!");

        Department department = optionalDepartment.get();
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (optionalCompany.isEmpty()) return ResponseEntity.status(409)
                .body("Company is not found!");
        Company company = optionalCompany.get();
        department.setCompany(company);
        department.setName(departmentDto.getName());
        departmentRepository.save(department);
        return ResponseEntity.status(202)
                .body("Department is successfully edited!");

    }

    // delete Department
    public ResponseEntity<?> deleteDepartment(Long id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isEmpty()) return ResponseEntity.status(409)
                .body("Department is not found!");

        boolean existsDepartmentInWorker = departmentRepository.existsDepartmentInWorker(id);
        if(existsDepartmentInWorker) return ResponseEntity.status(409)
                        .body("You can not delete this department for relationship!");
        departmentRepository.deleteById(id);
        return ResponseEntity.status(202)
                .body("Department is successfully deleted!");

    }
}

