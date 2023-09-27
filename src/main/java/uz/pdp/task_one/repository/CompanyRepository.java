package uz.pdp.task_one.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.task_one.entity.Company;

public interface CompanyRepository extends JpaRepository<Company,Long> {
    boolean existsByCorpName(String corpName);
    boolean existsByCorpNameAndIdNot(String corpName, Long id);

    @Query(value = "SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM company c\n" +
            "JOIN department d ON c.id = d.company_id WHERE c.id=:id",nativeQuery = true)
    boolean existsCompanyInDepartment(Long id);

}
