package com.semicolon.africa.House.Management.System.dtos.request;

import com.semicolon.africa.House.Management.System.data.models.Gender;
import com.semicolon.africa.House.Management.System.data.models.Room;
import com.semicolon.africa.House.Management.System.dtos.response.AssignRoomResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class CreateUserRequest {

    private String firstName;
    private String lastName;
    @Email
    private String email;
    @Min(6)
    private String password;
    @Min(6)
    private String confirmPassword;

    private Gender gender;
    private Room room;


}
