package com.akshay.projects.hotelApp.controller;

import com.akshay.projects.hotelApp.dto.Request.HotelSearchRequestDTO;
import com.akshay.projects.hotelApp.dto.Response.HotelResponseDTO;
import com.akshay.projects.hotelApp.service.Impl.InventoryServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
@Slf4j
public class HotelBrowseController {

    private final InventoryServiceImpl inventoryService;

    @GetMapping("/search")
    public ResponseEntity<Page<HotelResponseDTO>> searchHotels(@RequestBody HotelSearchRequestDTO request) {
        log.info("Request received to search hotels with city={} and startDate={} and endDate={} and roomsCount={}",  request.getCity(),
                request.getStartDate(),
                request.getEndDate(),
                request.getRoomsCount()
        );

       Page<HotelResponseDTO> page = inventoryService.searchHotels(request);
       return ResponseEntity
               .status(HttpStatus.OK)
               .body(page);
    }
}
