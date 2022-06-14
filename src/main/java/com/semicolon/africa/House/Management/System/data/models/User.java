package com.semicolon.africa.House.Management.System.data.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Builder
public class User {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    @Email
    private String email;
    @Min(6)
    private String password;
    @Min(6)
    private String confirmPassword;

    private Gender gender;
    private boolean isMadePayment;
//    private LocalDateTime localDateTime;
}
