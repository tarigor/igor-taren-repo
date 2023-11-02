package com.senla.hotel.dto.searchcriteria;

import com.senla.hotel.constant.GuestServicesSection;
import com.senla.hotel.constant.Ordering;
import com.senla.hotel.validator.annotation.EnumValidator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuestServicesSearchCriteria {
    private Long guestId;
    @EnumValidator(targetClassType = GuestServicesSection.class)
    private String sortBy;
    @EnumValidator(targetClassType = Ordering.class)
    private String sortOrder;
}
