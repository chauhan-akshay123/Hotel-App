package com.akshay.projects.hotelApp.dto.Response;

import com.akshay.projects.hotelApp.entity.HotelContactInfo;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonPropertyOrder({
        "id",
        "name",
        "city",
        "photos",
        "amenities",
        "contactInfo",
        "active",
        "createdAt",
        "updatedAt"
})
public class HotelResponseDTO {
 private Long id;
 private String name;
 private String city;
 private List<String> photos;
 private List<String> amenities;
 private HotelContactInfo contactInfo;
 private Boolean active;
 private LocalDateTime createdAt;
 private LocalDateTime updatedAt;
}
