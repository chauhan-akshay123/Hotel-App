package com.akshay.projects.hotelApp.service;

import com.akshay.projects.hotelApp.dto.Request.CreateRoomRequestDTO;
import com.akshay.projects.hotelApp.dto.Response.RoomResponseDTO;
import com.akshay.projects.hotelApp.dto.Response.SingleMessageResponseDTO;

import java.util.List;

public interface IRoomService {

    RoomResponseDTO createRoom(Long hotelId ,CreateRoomRequestDTO requestDTO);
    List<RoomResponseDTO> getAllRoomsInHotel(Long hotelId);
    RoomResponseDTO getRoomById(Long roomId);
    SingleMessageResponseDTO deleteRoomById(Long roomId);
}
