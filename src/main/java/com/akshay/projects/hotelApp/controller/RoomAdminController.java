package com.akshay.projects.hotelApp.controller;

import com.akshay.projects.hotelApp.dto.Request.CreateRoomRequestDTO;
import com.akshay.projects.hotelApp.dto.Response.RoomResponseDTO;
import com.akshay.projects.hotelApp.dto.Response.SingleMessageResponseDTO;
import com.akshay.projects.hotelApp.service.IRoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/admin/hotels/{hotelId}/rooms")
@RequiredArgsConstructor
public class RoomAdminController  {

    private final IRoomService roomService;

    @PostMapping
    public ResponseEntity<RoomResponseDTO> createRoom(@PathVariable Long hotelId, @Valid @RequestBody CreateRoomRequestDTO requestDTO) {
        log.info("Request received for creating a room");
        RoomResponseDTO responseDTO = roomService.createRoom(hotelId ,requestDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<RoomResponseDTO>> getAllRooms(@PathVariable Long hotelId) {
        log.info("Request received for fetching all the rooms with hotelId: {}", hotelId);
        List<RoomResponseDTO> response = roomService.getAllRoomsInHotel(hotelId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomResponseDTO> getRoomById(@PathVariable Long roomId) {
        log.info("Request received for fetching room by Id: {}" , roomId);
        RoomResponseDTO response = roomService.getRoomById(roomId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<SingleMessageResponseDTO> deleteRoomById(@PathVariable Long roomId) {
        log.info("Request received to delete room by Id: {}", roomId);
        SingleMessageResponseDTO response = roomService.deleteRoomById(roomId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
