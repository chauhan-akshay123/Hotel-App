package com.akshay.projects.hotelApp.dto.Request;

import com.akshay.projects.hotelApp.entity.HotelContactInfo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateHotelRequestDTO {

    @Size(min = 2, max = 100,
            message = "Hotel name must be between 2 and 100 characters")
    private String name;

    @Size(max = 100,
       message = "City cannot exceed 100 characters"
    )
    private String city;
    private String[] photos;
    private String[] amenities;
    @Valid
    private HotelContactInfo contactInfo;
}
