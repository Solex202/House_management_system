package com.semicolon.africa.House.Management.System.dtos.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApiResponse {

    private String message;
    private  boolean isSuccessful;
}
