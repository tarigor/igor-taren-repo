package com.senla.hotel.dto.searchcriteria;

import com.senla.hotel.constant.Ordering;
import com.senla.hotel.constant.RoomServiceSection;
import com.senla.hotel.validator.annotation.EnumValidator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomServiceSearchCriteria {
    @EnumValidator(targetClassType = RoomServiceSection.class)
    private String sortBy;
    @EnumValidator(targetClassType = Ordering.class)
    private String sortOrder;
}
