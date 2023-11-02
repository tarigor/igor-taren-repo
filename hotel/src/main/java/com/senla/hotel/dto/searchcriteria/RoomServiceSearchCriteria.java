package com.senla.hotel.dto.searchcriteria;

import com.senla.hotel.constant.Ordering;
import com.senla.hotel.constant.RoomServiceSection;
import com.senla.hotel.validator.annotation.EnumValidator;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomServiceSearchCriteria {
    @NotEmpty(message = "{sortBy} parameter must be exist")
    @EnumValidator(targetClassType = RoomServiceSection.class)
    private String sortBy;
    @NotEmpty(message = "{sortOrder} parameter must be exist")
    @EnumValidator(targetClassType = Ordering.class)
    private String sortOrder;
}
