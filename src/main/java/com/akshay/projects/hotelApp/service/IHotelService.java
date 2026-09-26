package com.akshay.projects.hotelApp.service;

import com.akshay.projects.hotelApp.dto.Request.CreateHotelRequestDTO;
import com.akshay.projects.hotelApp.dto.Request.UpdateHotelRequestDTO;
import com.akshay.projects.hotelApp.dto.Response.HotelResponseDTO;
import com.akshay.projects.hotelApp.dto.Response.SingleMessageResponseDTO;

public interface IHotelService {

    HotelResponseDTO createNewHotel(CreateHotelRequestDTO requestDTO);

    HotelResponseDTO getHotelById(Long id);

    HotelResponseDTO updateHotelById(Long id, UpdateHotelRequestDTO requestDTO);

    SingleMessageResponseDTO deleteHotelById(Long id);

    HotelResponseDTO activateHotelbyId(Long id);

    HotelResponseDTO deactivateHotelById(Long id);
}
