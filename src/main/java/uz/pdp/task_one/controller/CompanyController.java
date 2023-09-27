package uz.pdp.task_one.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task_one.exception.ExceptionMessage;
import uz.pdp.task_one.payload.CompanyDto;
import uz.pdp.task_one.service.CompanyService;

@RestController
@Validated
@RequestMapping("/api/company")
public class CompanyController extends ExceptionMessage {
    CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<?> addCompany(@Valid @RequestBody CompanyDto companyDto) {
        return companyService.addCompany(companyDto);
    }

    @GetMapping
    public ResponseEntity<?> getCompaniesByPage(@Valid @RequestParam Integer page){
        return companyService.getCompaniesByPage(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCompany(@PathVariable Long id){
        return companyService.getCompany(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editCompany(@PathVariable Long id, @Valid @RequestBody CompanyDto companyDto){
        return companyService.editCompany(id,companyDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable Long id){
        return companyService.deleteCompany(id);
    }

}
