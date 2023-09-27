package uz.pdp.task_one.payload;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WorkerDto {
    @NotNull(message = "{workerDto.name.notEmpty}")
    private String name;

    @NotNull(message = "{workerDto.phoneNumber.notEmpty}")
    private String phoneNumber;

    @NotNull(message = "{workerDto.street.notEmpty}")
    private String street;

    @NotNull(message = "{workerDto.homeNumber.notEmpty}")
    private Integer homeNumber;

    @NotNull(message = "{workerDto.departmentId.notEmpty}")
    private Long departmentId;
}
