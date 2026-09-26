package com.akshay.projects.hotelApp.service.Impl;

import com.akshay.projects.hotelApp.dto.Request.CreateHotelRequestDTO;
import com.akshay.projects.hotelApp.dto.Response.HotelResponseDTO;
import com.akshay.projects.hotelApp.entity.Hotel;
import com.akshay.projects.hotelApp.exception.DuplicateResourceException;
import com.akshay.projects.hotelApp.exception.ResourceNotFoundException;
import com.akshay.projects.hotelApp.repository.HotelRepository;
import com.akshay.projects.hotelApp.service.IHotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements IHotelService {

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;

    @Override
    public HotelResponseDTO createNewHotel(CreateHotelRequestDTO requestDTO) {
      log.info("Creating hotel with name={} and city={}", requestDTO.getName(), requestDTO.getCity());

      boolean exists = hotelRepository.existsByNameIgnoreCaseAndCityIgnoreCase(
              requestDTO.getName(),
              requestDTO.getCity()
      );

        if (exists) {
            throw new DuplicateResourceException
                    ( "Hotel already exists with name: " + requestDTO.getName() + " in city: " + requestDTO.getCity() );
      }

      Hotel hotel = modelMapper.map(requestDTO, Hotel.class);
      hotel.setActive(false);
      Hotel savedHotel = hotelRepository.save(hotel);

      log.info("Hotel created successfull with id = {}",
              savedHotel.getId());

      return modelMapper.map(savedHotel, HotelResponseDTO.class);
    }

    @Override
    public HotelResponseDTO getHotelById(Long id) {
        log.info("Fetching the hotel with Id: {}", id);

        Hotel hotel = hotelRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Hotel not found with Id: {} " + id)
        );

        return modelMapper.map(hotel, HotelResponseDTO.class);
    }
}
