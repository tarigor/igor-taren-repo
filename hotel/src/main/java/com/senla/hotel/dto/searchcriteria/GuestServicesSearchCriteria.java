package com.senla.hotel.dto.searchcriteria;

import com.senla.hotel.enums.GuestServicesSection;
import com.senla.hotel.enums.Ordering;
import com.senla.hotel.validator.annotation.EnumValidator;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuestServicesSearchCriteria {
    @NotEmpty(message = "{guestId} parameter must be exist")
    private Long guestId;
    @NotEmpty(message = "{sortBy} parameter must be exist")
    @EnumValidator(targetClassType = GuestServicesSection.class)
    private String sortBy;
    @NotEmpty(message = "{sortOrder} parameter must be exist")
    @EnumValidator(targetClassType = Ordering.class)
    private String sortOrder;
}
