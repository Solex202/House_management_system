package com.semicolon.africa.House.Management.System.dtos.request;

import com.semicolon.africa.House.Management.System.data.models.Gender;
import com.semicolon.africa.House.Management.System.data.models.Payment;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Builder
@Data
//@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Validated
public class BookRoomRequest {
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
    private Payment makePayment;
    private LocalDateTime localDateTime;

    public BookRoomRequest(){
        this.localDateTime = LocalDateTime.now();
    }

}
