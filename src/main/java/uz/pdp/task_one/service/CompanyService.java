package uz.pdp.task_one.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.task_one.entity.Address;
import uz.pdp.task_one.entity.Company;
import uz.pdp.task_one.payload.CompanyDto;
import uz.pdp.task_one.repository.AddressRepository;
import uz.pdp.task_one.repository.CompanyRepository;

import java.net.http.HttpResponse;
import java.util.Optional;

@Service
public class CompanyService {

    CompanyRepository companyRepository;
    AddressRepository addressRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, AddressRepository addressRepository) {
        this.companyRepository = companyRepository;
        this.addressRepository = addressRepository;
    }

    //ADD NEW COMPANY
    public ResponseEntity<?> addCompany(CompanyDto companyDto) {
        Address address = new Address();
        address.setStreet(companyDto.getStreet());
        address.setHomeNumber(companyDto.getHomeNumber());
        Address savedAddress = addressRepository.save(address);
        Company company = new Company();
        boolean existsByCorpName = companyRepository.existsByCorpName(companyDto.getCorpName());
        if (existsByCorpName) return ResponseEntity.status(409)
                .body("This corpName is already exist!");
        company.setAddress(savedAddress);
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        companyRepository.save(company);
        return ResponseEntity.status(202).body("Company is successfully added!");
    }

    // GET COMPANIES BY PAGE

    public ResponseEntity<?> getCompaniesByPage(Integer page) {
        int size = 10;

        Pageable pageable = PageRequest.of(page, size);
        Page<Company> companies = companyRepository.findAll(pageable);
        return ResponseEntity.status(202).body(companies);

    }

    //Get company by id
    public ResponseEntity<?> getCompany(Long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isEmpty()) return ResponseEntity.status(409).body("NOt found!");

        return ResponseEntity.status(202).body(optionalCompany.get());
    }

    // Edit company

    public ResponseEntity<?> editCompany(Long id, CompanyDto companyDto) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isEmpty()) return ResponseEntity.status(409)
                .body("Company is not found by this id!");

        boolean existsByCorpNameAndIdNot = companyRepository.existsByCorpNameAndIdNot(companyDto.getCorpName(), id);
        if (existsByCorpNameAndIdNot) return ResponseEntity.status(409).body("This corpName is already exist!");
        Company company = optionalCompany.get();
        Address address = company.getAddress();
        address.setHomeNumber(companyDto.getHomeNumber());
        address.setStreet(companyDto.getStreet());
        Address savedAddress = addressRepository.save(address);
        company.setAddress(savedAddress);
        company.setDirectorName(companyDto.getDirectorName());
        company.setCorpName(companyDto.getCorpName());
        companyRepository.save(company);
        return ResponseEntity.status(202).body("Company is successfully edited!");


    }

    // Delete by id

    public ResponseEntity<?> deleteCompany(Long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isEmpty()) return ResponseEntity.status(409).body("Not found!");
        boolean existsCompanyInDepartment = companyRepository.existsCompanyInDepartment(id);
        if(existsCompanyInDepartment) return ResponseEntity.status(409).body("You can not delete this Company for relationship!");

        companyRepository.deleteById(id);
        return ResponseEntity.status(202).body("Successfully deleted!");

    }

}
