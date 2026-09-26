package com.akshay.projects.hotelApp.dto.Request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateRoomRequestDTO {

    @NotBlank(message = "Room type is required")
    @Size(max = 100, message = "Room type cannot exceed 100 characters")
    private String type;

    @NotNull(message = "Base price is required")
    @DecimalMin(
            value = "0.01",
            message = "Base price must be greater than 0"
    )
    private BigDecimal basePrice;

    private String[] photos;
    private String[] amenities;

    @NotNull(message = "Total room count is required")
    @Min(value = 1, message = "Total room count must be at least 1")
    private Integer totalCount;

    @NotNull(message = "Capacity is required")
    @Min(value = 1, message = "Capacity must be at least 1")
    private Integer capacity;
}
