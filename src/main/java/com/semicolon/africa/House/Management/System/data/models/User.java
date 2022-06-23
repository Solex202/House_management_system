package com.semicolon.africa.House.Management.System.data.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
public class User {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
    private Gender gender;
    private Payment payment;
    private boolean paymentStatus;
    private LocalDateTime bookingTime;
    private RentDuration rentalDuration;
//    private Date rentExpirationDate;

}
