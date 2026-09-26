package com.akshay.projects.hotelApp.dto.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SingleMessageResponseDTO {
    private String message;
}
