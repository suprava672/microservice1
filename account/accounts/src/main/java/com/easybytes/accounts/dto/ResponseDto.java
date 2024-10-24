package com.easybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(
        name = "Response",
        description = "hold the response information"
)
public class ResponseDto {
    @Schema(
            description = "status code"
    )
    private String statusCode;
    @Schema(
            description = "ststus message"
    )
    private String statusMsg;

}
