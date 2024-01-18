package com.senla.adsservice.dto;

import com.senla.database.entity.Advertisement;
import com.senla.database.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleDto {
    private Long id;
    private User buyer;
    private Advertisement advertisement;
    private Date date;
}
