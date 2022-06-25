package com.semicolon.africa.House.Management.System.dtos;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data

public class UserDto {
    @Id
    private String id;
    private String message;
    private String email;
    private LocalDateTime bookingTime;
}
