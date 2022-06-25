package com.semicolon.africa.House.Management.System.dtos.request;

import com.semicolon.africa.House.Management.System.data.models.Gender;
import com.semicolon.africa.House.Management.System.data.models.Payment;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
public class BookRoomRequest
{
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private Payment payment;
    private LocalDateTime bookingTime = LocalDateTime.now();

    public BookRoomRequest(String id, String firstName,String lastName,String email,Gender gender,Payment payment)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.payment = payment;
        bookingTime = LocalDateTime.now();
    }

}
