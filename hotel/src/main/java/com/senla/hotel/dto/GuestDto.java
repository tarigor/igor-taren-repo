package com.senla.hotel.dto;

import com.senla.hotel.enums.Role;
import com.senla.hotel.validator.annotation.EnumValidator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestDto {
    @NotNull
    @NotEmpty(message = "The field `First Name` must be not empty")
    @Size(min = 3, max = 30, message = "`First Name` must be between {min} and {max} characters long")
    private String firstName;
    @NotNull
    @NotEmpty(message = "The field `Last Name` must be not empty")
    @Size(min = 3, max = 30, message = "`Last Name` must be between {min} and {max} characters long")
    private String lastName;
    @NotNull
    @NotEmpty(message = "The field `email` must be not empty")
    @Email
    private String email;
    @NotNull(message = "The field `password` must be not empty")
    @NotEmpty
    private String password;
    @EnumValidator(targetClassType = Role.class)
    @NotNull(message = "The field `role` must be not empty")
    @NotEmpty
    private String role;

    public GuestDto(String firstName, String lastName, String email, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }
}
