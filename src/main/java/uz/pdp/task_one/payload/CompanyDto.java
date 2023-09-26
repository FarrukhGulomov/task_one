package uz.pdp.task_one.payload;


import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import uz.pdp.task_one.entity.Address;


@Data
public class CompanyDto {
    @NotBlank(message = "{companyDto.corpName.notEmpty}")
    private String corpName;

    @NotBlank(message = "{companyDto.directorName.notEmpty}")
    private String directorName;

    @NotBlank(message = "{companyDto.addressId.notEmpty}")
    private Long addressId;

}
