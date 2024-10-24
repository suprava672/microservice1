package com.easybytes.accounts.controller;

import com.easybytes.accounts.constants.AccountsConstant;
import com.easybytes.accounts.dto.CustomerDto;
import com.easybytes.accounts.dto.ErrorResponseDto;
import com.easybytes.accounts.dto.ResponseDto;
import com.easybytes.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
@Tag(
        name = "Crud RestAPIs for Accounts in easybanks",
        description = "Crud Rest APIs for create,delete,ftech,update accounts details"
)
@RestController
@RequestMapping(path="/api",produces={MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountsController {
    private IAccountsService iAccountsService;
    @Operation(
            summary = "create Account  RestApi ",
            description ="Rest API to create new account for customer"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTPSTATUS OK"

    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){
        iAccountsService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstant.STATUS201,AccountsConstant.MESSAGE201));

    }
    @Operation(
            summary = "Fetch Account Rest API",
            description = "Rest API for fetch account based upon mobile number"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS OK"
    )
    @GetMapping ("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountsDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})",
                                                                        message = "The mobile number should be 10 digits") String mobileNumber){
        CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }
    @Operation(
            summary = "Update Account Rest API",
            description = "Rest API for update account"
    )
    @ApiResponses({
            @ApiResponse(

                    responseCode = "200",
                    description = "HTTP STATUS OK"
            ),
           @ApiResponse(
                   responseCode = "500",
                   description = "HTTP STATUS Internal Server Error",
                   content = @Content(
                           schema = @Schema(implementation = ErrorResponseDto.class)
                   )
           )
    }
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = iAccountsService.updateAccount(customerDto);

        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto("200", "Account updated successfully."));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto("500", "An error occurred, please try again or contact Dev team."));
        }
    }
@Operation(
        summary = "Delete Account Rest API",
        description = "Rest API for delete account based on mobile number"
)
@ApiResponses({
        @ApiResponse(
                responseCode = "201",
                description = "HTTP STATUS OK"
        ),
        @ApiResponse(
                responseCode = "500",
                description = "HTTP STATUS Internal server error"

        )

}

)
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto>deleteAccountDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})"
            ,message = "The mobile number should be 10 digits")String mobileNumber){
            boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
            if(isDeleted){
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountsConstant.STATUS200,AccountsConstant.MESSAGE200));

            }else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(AccountsConstant.STATUS500, AccountsConstant.MESSAGE500));
            }

        }

}


