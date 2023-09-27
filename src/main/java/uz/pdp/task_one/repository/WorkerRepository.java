package uz.pdp.task_one.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.task_one.entity.Worker;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Long id);
}
