package com.vikasLearning.springboot.dto;
//Don't use sensitive information to Dto.

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Schema(
        description = "User Dto Model Information"
)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    //User first name should not be null or empty
    @NotEmpty(message = "User first name should not be null or empty")
    @Schema(
            description = "User First Name"
    )
    private String firstName;

    //User last name should not be null or empty
    @NotEmpty(message = "User last name should not be null or empty")
    @Schema(
            description = "User Last Name"
    )
    private String lastName;

    //User email should not be null or empty
    //Email address should be valid
    @Schema(
            description = "User Email Address"
    )
    @NotEmpty(message = "User Email should not be null or empty")
    @Email(message = "Email address should be valid.")
    private String email;
}
