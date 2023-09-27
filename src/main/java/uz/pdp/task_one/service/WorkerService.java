package uz.pdp.task_one.service;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.task_one.entity.Address;
import uz.pdp.task_one.entity.Department;
import uz.pdp.task_one.entity.Worker;
import uz.pdp.task_one.exception.ExceptionMessage;
import uz.pdp.task_one.payload.WorkerDto;
import uz.pdp.task_one.repository.AddressRepository;
import uz.pdp.task_one.repository.DepartmentRepository;
import uz.pdp.task_one.repository.WorkerRepository;

import java.util.Optional;

@Service
public class WorkerService  {

    WorkerRepository workerRepository;
    DepartmentRepository departmentRepository;
    AddressRepository addressRepository;

    public WorkerService(WorkerRepository workerRepository, DepartmentRepository departmentRepository, AddressRepository addressRepository) {
        this.workerRepository = workerRepository;
        this.departmentRepository = departmentRepository;
        this.addressRepository = addressRepository;
    }

    // add worker
    public ResponseEntity<?> addWorker(WorkerDto workerDto) {
        Worker worker = new Worker();
        Address address = new Address();
        address.setStreet(workerDto.getStreet());
        address.setHomeNumber(workerDto.getHomeNumber());
        Address savedAddress = addressRepository.save(address);

        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (optionalDepartment.isEmpty()) return ResponseEntity.status(409)
                .body("Department is not found!");
        boolean existsByPhoneNumber = workerRepository.existsByPhoneNumber(workerDto.getPhoneNumber());
        if(existsByPhoneNumber) return ResponseEntity.status(409)
                .body("This phone number is already exist!");
        Department department = optionalDepartment.get();
        worker.setDepartment(department);
        worker.setAddress(savedAddress);
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        workerRepository.save(worker);
        return ResponseEntity.status(202)
                .body("Worker is successfully added!");
    }

    // get worker by page

    public ResponseEntity<?> getWorkersByPage(Integer page) {
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        Page<Worker> workers = workerRepository.findAll(pageable);
        return ResponseEntity.status(202).body(workers);
    }

    // get worker by id
    public ResponseEntity<?> getWorker(Long id) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (optionalWorker.isEmpty()) return ResponseEntity.status(409)
                .body("Worker is not found!");
        return ResponseEntity.status(202)
                .body(optionalWorker.get());
    }

    // edit worker
    public ResponseEntity<?> editWorker(Long id, WorkerDto workerDto) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (optionalWorker.isEmpty()) return ResponseEntity.status(409)
                .body("Worker is not found!");

        Worker worker = optionalWorker.get();
        Address address = worker.getAddress();
        address.setHomeNumber(workerDto.getHomeNumber());
        address.setStreet(workerDto.getStreet());
        Address savedAddress = addressRepository.save(address);

        Optional<Department> optionalDepartment = departmentRepository.findById(workerDto.getDepartmentId());
        if (optionalDepartment.isEmpty()) return ResponseEntity.status(409)
                .body("Department is not found!");
        boolean existsByPhoneNumberAndIdNot = workerRepository.existsByPhoneNumberAndIdNot(workerDto.getPhoneNumber(), id);
        if(existsByPhoneNumberAndIdNot) return ResponseEntity.status(409)
                .body("This phone number is already exist!");
        Department department = optionalDepartment.get();
        worker.setAddress(savedAddress);
        worker.setDepartment(department);
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        workerRepository.save(worker);
        return ResponseEntity.status(202)
                .body("Worker is successfully edited!");

    }

    // delete worker
    public ResponseEntity<?> deleteWorker(Long id) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (optionalWorker.isEmpty()) return ResponseEntity.status(409)
                .body("Worker is not found!");

        workerRepository.deleteById(id);
        return ResponseEntity.status(202)
                .body("Worker is successfully deleted!");
    }

}
