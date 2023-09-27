package uz.pdp.task_one.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task_one.exception.ExceptionMessage;
import uz.pdp.task_one.payload.WorkerDto;
import uz.pdp.task_one.service.WorkerService;

@RestController
@Validated
@RequestMapping("/api/worker")
public class WorkerController extends ExceptionMessage {

    WorkerService workerService;

    @Autowired
    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @PostMapping
    public ResponseEntity<?> addWorker(@Valid @RequestBody WorkerDto workerDto) {
        return workerService.addWorker(workerDto);
    }

    @GetMapping
    public ResponseEntity<?> getWorkers(@RequestParam Integer page) {
        return workerService.getWorkersByPage(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWorker(@PathVariable Long id) {
        return workerService.getWorker(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editWorker(@PathVariable Long id, @Valid @RequestBody WorkerDto workerDto) {
        return workerService.editWorker(id, workerDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWorker(@PathVariable Long id) {
        return workerService.deleteWorker(id);
    }

}
