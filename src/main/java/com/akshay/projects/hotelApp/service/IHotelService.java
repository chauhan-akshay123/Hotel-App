package com.akshay.projects.hotelApp.service;

import com.akshay.projects.hotelApp.dto.Request.CreateHotelRequestDTO;
import com.akshay.projects.hotelApp.dto.Response.HotelResponseDTO;

public interface IHotelService {

    HotelResponseDTO createNewHotel(CreateHotelRequestDTO requestDTO);

    HotelResponseDTO getHotelById(Long id);
}
