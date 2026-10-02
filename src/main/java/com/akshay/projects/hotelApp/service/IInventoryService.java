package com.akshay.projects.hotelApp.service;

import com.akshay.projects.hotelApp.dto.Request.HotelSearchRequestDTO;
import com.akshay.projects.hotelApp.dto.Response.HotelResponseDTO;
import com.akshay.projects.hotelApp.entity.Room;
import org.springframework.data.domain.Page;

public interface IInventoryService {

    void InitializeRoomForAYear(Room room);

    void deleteFutureInvetories(Room room);

    void deleteInventoriesByRoom(Room room);

    Page<HotelResponseDTO> searchHotels(HotelSearchRequestDTO request);
}
