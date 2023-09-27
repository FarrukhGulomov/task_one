package uz.pdp.task_one.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.task_one.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department,Long> {

    boolean existsDepartmentByNameAndCompanyId(String name, Long company_id);

    @Query(value = "SELECT CASE WHEN COUNT(d) > 0 THEN TRUE ELSE FALSE END FROM department d\n" +
            "JOIN worker w ON d.id=w.department_id   WHERE d.id=:id",nativeQuery = true)
    boolean existsDepartmentInWorker(Long id);
}
