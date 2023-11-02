package com.senla.hotel.dto.searchcriteria;

import com.senla.hotel.constant.Ordering;
import com.senla.hotel.constant.RoomSection;
import com.senla.hotel.validator.annotation.EnumValidator;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RoomSearchCriteria implements Serializable {
    @NotEmpty(message = "{getOnlyAvailable} parameter must be exist")
    @Pattern(regexp = "^true$|^false$", message = "allowed input: true or false")
    private String getOnlyAvailable;
    @NotEmpty(message = "{sortBy} parameter must be exist")
    @EnumValidator(targetClassType = RoomSection.class)
    private String sortBy;
    @NotEmpty(message = "{sortOrder} parameter must be exist")
    @EnumValidator(targetClassType = Ordering.class)
    private String sortOrder;
}
