package com.semicolon.africa.House.Management.System.dtos.response;

import com.semicolon.africa.House.Management.System.data.models.Gender;
import com.semicolon.africa.House.Management.System.data.models.Payment;
import com.semicolon.africa.House.Management.System.data.models.RentDuration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindBookingResponse {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    @Email
    private String email;
    private Gender gender;
    private Payment payment;
    private LocalDateTime bookingTime;
    private RentDuration rentalDuration;
}
