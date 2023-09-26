package uz.pdp.task_one.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task_one.entity.Address;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
