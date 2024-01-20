package com.senla.adsservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResponseDto {
    private String accessToken;
    private String expirationTime;
}
