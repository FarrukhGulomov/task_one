package uz.pdp.task_one.payload;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import uz.pdp.task_one.entity.Company;

@Data
public class DepartmentDto {

    @NotBlank(message = "{departmentDto.name.notEmpty}")
    private String name;
    @NotNull(message = "{departmentDto.companyId.notEmpty}")
    private Long companyId;

}
