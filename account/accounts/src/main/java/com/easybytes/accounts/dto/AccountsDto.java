package com.easybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name="Accounts",
        description = "holds the account infromation"
)
public class AccountsDto {
    @Schema(
            description = "account number for easy bytes"
    )
    @NotEmpty(message = "Account number should not be empty")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "The mobile number should be 10 digits")
    private Long accountNumber;
    @Schema(
            description = "account type"
    )
    @NotEmpty(message = "account type should not be empty")
    private String accountType;
    @Schema(
            description = "branch addres for easy bank"
    )
    @NotEmpty(message = "branch address should not be empty")
    private String branchAddress;
    private CustomerDto customerDto;
}
