package com.semicolon.africa.House.Management.System.dtos.request;

import com.semicolon.africa.House.Management.System.data.models.Gender;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
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
    private boolean isMadePayment;
    private LocalDateTime localDateTime;

//    public BookRoomRequest(String firstName, String lastName, String email, String password, String confirmPassword, Gender gender, boolean isMadePayment){
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.password = password;
//        this.confirmPassword = confirmPassword;
//        this.gender = gender;
//        this.isMadePayment = isMadePayment;
//        this.localDateTime = LocalDateTime.now();
//    }

}
