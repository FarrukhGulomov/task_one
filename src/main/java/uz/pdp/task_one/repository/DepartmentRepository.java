package uz.pdp.task_one.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task_one.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
