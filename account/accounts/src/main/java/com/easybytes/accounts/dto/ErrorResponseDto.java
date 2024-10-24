package com.easybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@Schema(
        name="ErrorResponse",
        description = "giving the error response"
)
@Data @AllArgsConstructor
public class ErrorResponseDto {
    @Schema(
            description = "api path for esay bank account"
    )
    private String apiPath;
    @Schema(
            description = "errorCode for easy bank account"
    )
    private HttpStatus errorCode;
    @Schema(
            description = "errorMessage for account"
    )
    private String errorMessage;
    @Schema(
            description = "errorTime for accounts"
    )
    private LocalDateTime errorTime;
}
