package com.akshay.projects.hotelApp.service.Impl;

import com.akshay.projects.hotelApp.dto.Request.CreateRoomRequestDTO;
import com.akshay.projects.hotelApp.dto.Response.RoomResponseDTO;
import com.akshay.projects.hotelApp.dto.Response.SingleMessageResponseDTO;
import com.akshay.projects.hotelApp.entity.Hotel;
import com.akshay.projects.hotelApp.entity.Room;
import com.akshay.projects.hotelApp.exception.ResourceNotFoundException;
import com.akshay.projects.hotelApp.repository.HotelRepository;
import com.akshay.projects.hotelApp.repository.RoomRepository;
import com.akshay.projects.hotelApp.service.IRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomServiceImpl implements IRoomService {

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;

    @Override
    public RoomResponseDTO createRoom(Long hotelId ,CreateRoomRequestDTO requestDTO) {
        log.info("Creating a new room");

        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(
                () -> new ResourceNotFoundException("Hotel not found with id: " + hotelId)
        );

        Room room = modelMapper.map(requestDTO, Room.class);
        room.setHotel(hotel);
        Room savedRoom = roomRepository.save(room);
        log.info("Room is created successfully with id: {}", savedRoom.getId());

        // TODO: Create inventory as soon as room is created if hotel is active

        return modelMapper.map(room, RoomResponseDTO.class);
    }

    @Override
    public List<RoomResponseDTO> getAllRoomsInHotel(Long hotelId) {
        log.info("Fetching rooms in a hotel with Id: {}", hotelId);
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(
                () ->
                    new ResourceNotFoundException("Hotel not found with Id: " + hotelId)
                );

        return hotel.getRooms()
                .stream()
                .map((element) -> modelMapper.map(element, RoomResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public RoomResponseDTO getRoomById(Long roomId) {
        log.info("Fetching room with Id: {}", roomId);

        Room room = roomRepository.findById(roomId).orElseThrow(
                () -> new ResourceNotFoundException("Room not found with Id: " + roomId)
        );

        return modelMapper.map(room, RoomResponseDTO.class);
    }

    @Override
    public SingleMessageResponseDTO deleteRoomById(Long roomId) {
        log.info("Deleting room with Id: {}", roomId);

        boolean exists = roomRepository.existsById(roomId);
        if(!exists) {
            throw new ResourceNotFoundException("Room not found with Id: " + roomId);
        }
        roomRepository.deleteById(roomId);
        log.info("Room with id: {} deleted successfully", roomId);

        return SingleMessageResponseDTO.builder()
                .message("Room deleted successfully")
                .build();
    }
}
