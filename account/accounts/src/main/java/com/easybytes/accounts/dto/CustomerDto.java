package com.easybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.Parent;

@Data
@Schema(
        name = "Customer",
        description = "schema to hold information about customer and accounts"
)
public class CustomerDto {
    @Schema(
            description = "name of the customer,example=suprava"
    )
    @NotEmpty(message ="name can not be empty")
    @Size(min=6,max = 40,message ="The of the name should be between 6 and 40")
    private  String name;
    @Schema(
            description = "email of the customer, example=suprava09@gmail.com"
    )
    @NotEmpty(message ="email can not be empty")
    @Email(message = "email should be valid value")
    private String email;
    @Schema(
            description = "mobile number of the customer, example=5551234567"
    )
    @Pattern(regexp = "(^$|[0-9]{10})",message = "The mobile number should be 10 digits")
    private String mobileNumber;
    @Schema(
            description = "account details of the customer"
    )
    private AccountsDto accountsDto;
}
