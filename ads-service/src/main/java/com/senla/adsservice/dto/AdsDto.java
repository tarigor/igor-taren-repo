package com.senla.adsservice.dto;

import com.senla.adsdatabase.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdsDto {
    private Long id;
    private User seller;
    private String advMessage;
    private int priority;
}
