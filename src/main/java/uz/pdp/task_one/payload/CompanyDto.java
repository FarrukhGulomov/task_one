package uz.pdp.task_one.payload;


import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import uz.pdp.task_one.entity.Address;


@Data
public class CompanyDto {
    @NotNull(message = "{companyDto.corpName.notEmpty}")
    private String corpName;

    @NotNull(message = "{companyDto.directorName.notEmpty}")
    private String directorName;

    @NotNull(message = "{companyDto.street.notEmpty}")
    private String street;

    @NotNull(message = "{companyDto.homeNumber.notEmpty}")
    private Integer homeNumber;

}
