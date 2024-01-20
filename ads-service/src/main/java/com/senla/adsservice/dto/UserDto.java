package com.senla.adsservice.dto;

import com.senla.adsservice.enums.UserRole;
import com.senla.adsservice.validator.annotation.EnumValidator;
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
public class UserDto {
    private Long id;
    @Size(min = 3, max = 30, message = "`First Name` must be between {min} and {max} characters long")
    private String firstName;
    @Size(min = 3, max = 30, message = "`Last Name` must be between {min} and {max} characters long")
    private String lastName;
    @Size(min = 3, max = 50, message = "`Company Name` must be between {min} and {max} characters long")
    private String companyName;
    @NotNull
    @NotEmpty(message = "The field `email` must be not empty")
    @Email
    private String email;
    @EnumValidator(targetClassType = UserRole.class)
    @NotNull(message = "The field `role` must be not empty")
    @NotEmpty
    private String userRole;
    @NotNull(message = "The field `password` must be not empty")
    @NotEmpty
    private String password;

    public UserDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
