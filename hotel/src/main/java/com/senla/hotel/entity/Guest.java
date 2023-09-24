package com.senla.hotel.entity;

import com.senla.container.ConfigProperty;
import com.senla.container.FieldProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigProperty(configFileName = "Guest")
public class Guest {
    private long id;
    @FieldProperty
    private String firstName;
    @FieldProperty
    private String lastName;

    public Guest(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
