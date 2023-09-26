package uz.pdp.task_one.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task_one.entity.Company;

public interface CompanyRepository extends JpaRepository<Company,Long> {
}
