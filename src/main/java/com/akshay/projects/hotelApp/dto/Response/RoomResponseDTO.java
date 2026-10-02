package com.akshay.projects.hotelApp.dto.Response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@JsonPropertyOrder({
       "id",
        "hotelId",
        "type",
        "basePrice",
        "photos",
        "amenities",
        "totalCount",
        "capacity",
        "createdAt",
        "updatedAt"
})
public class RoomResponseDTO {

    private Long id;
    private Long hotelId;
    private String type;
    private BigDecimal basePrice;
    private String[] photos;
    private String[] amenities;
    private Integer totalCount;
    private Integer capacity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
