package com.akshay.projects.hotelApp.controller;

import com.akshay.projects.hotelApp.dto.Request.CreateHotelRequestDTO;
import com.akshay.projects.hotelApp.dto.Response.HotelResponseDTO;
import com.akshay.projects.hotelApp.service.IHotelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/hotels")
@RequiredArgsConstructor
@Slf4j
public class HotelAdminController {

    private final IHotelService hotelService;

    @PostMapping
    public ResponseEntity<HotelResponseDTO> createNewHotel(@Valid @RequestBody CreateHotelRequestDTO requestDTO) {
        log.info( "Request received to create hotel with name={} and city={}",
                requestDTO.getName(),
                requestDTO.getCity()
        );

        HotelResponseDTO responseDTO = hotelService.createNewHotel(requestDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDTO);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelResponseDTO> getHotelById(@PathVariable Long hotelId) {
        log.info("Request received to fetch hotel with id={} ", hotelId);
        HotelResponseDTO responseDTO = hotelService.getHotelById(hotelId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseDTO);
    }
}
