package com.akshay.projects.hotelApp.dto.Request;

import com.akshay.projects.hotelApp.entity.HotelContactInfo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class CreateHotelRequestDTO {

    @NotBlank(message = "Hotel name is required")
    @Size(min = 2, max = 100)
    private String name;

    @NotBlank(message = "City name is required")
    @Size(max = 100)
    private String city;

    private List<String> photos;

    private List<String> amenities;

    @Valid
    @NotNull(message = "Contact information is required")
    private HotelContactInfo contactInfo;
}
