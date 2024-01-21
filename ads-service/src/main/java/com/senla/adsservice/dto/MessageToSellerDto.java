package com.senla.adsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageToSellerDto {
    private long id;
    private long sellerId;
    private String message;
}
