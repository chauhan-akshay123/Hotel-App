package com.akshay.projects.hotelApp.service.Impl;

import com.akshay.projects.hotelApp.dto.Request.CreateHotelRequestDTO;
import com.akshay.projects.hotelApp.dto.Request.UpdateHotelRequestDTO;
import com.akshay.projects.hotelApp.dto.Response.HotelResponseDTO;
import com.akshay.projects.hotelApp.dto.Response.SingleMessageResponseDTO;
import com.akshay.projects.hotelApp.entity.Hotel;
import com.akshay.projects.hotelApp.entity.HotelContactInfo;
import com.akshay.projects.hotelApp.exception.*;
import com.akshay.projects.hotelApp.exception.IllegalStateException;
import com.akshay.projects.hotelApp.repository.HotelRepository;
import com.akshay.projects.hotelApp.service.IHotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements IHotelService {

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
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

    @Override
    @Transactional
    public HotelResponseDTO updateHotelById(Long id, UpdateHotelRequestDTO requestDTO) {
        log.info("Updating hotel with Id: {}", id);

        Hotel hotel = hotelRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Hotel not found with Id: {} " + id)
        );

        if(requestDTO.getName()!=null) {
            hotel.setName(requestDTO.getName().trim());
        }
        if(requestDTO.getCity()!=null) {
            hotel.setCity(requestDTO.getCity().trim());
        }
        if(requestDTO.getPhotos()!=null) {
            hotel.setPhotos(requestDTO.getPhotos());
        }
        if(requestDTO.getAmenities()!=null) {
            hotel.setAmenities(requestDTO.getAmenities());
        }

        if(requestDTO.getContactInfo()!=null) {
            hotel.setContactInfo(
                    modelMapper.map(
                            requestDTO.getContactInfo(),
                            HotelContactInfo.class
                    )
            );
        }

        Hotel updateHotel = hotelRepository.save(hotel);

        log.info("Hotel updated successfully with id = {}", updateHotel.getId());

        return modelMapper.map(
                updateHotel,
                HotelResponseDTO.class
        );
    }

    @Override
    public SingleMessageResponseDTO deleteHotelById(Long id) {
        log.info("Deleting the hotel with id: {}", id);

        Hotel hotel = hotelRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Hotel not found with id: " + id)
        );

        hotelRepository.deleteById(id);
        // TODO: delete the future invetories for this hotel

        return SingleMessageResponseDTO.builder()
                .message("Hotel has been deleted successfully")
                .build();
    }

    @Override
    @Transactional
    public HotelResponseDTO activateHotelbyId(Long id) {
        log.info("Activating the hotel with id: {}", id);

        Hotel hotel = hotelRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Hotel not found with id: " + id)
        );
        if(Boolean.TRUE.equals(hotel.getActive())) {
            throw new HotelAlreadyActiveException("Hotel is already active with id: " + id);
        }
        hotel.setActive(true);
    // TODO: create inventory for all the rooms for this hotel

        Hotel updatedHotel = hotelRepository.save(hotel);
        log.info("Hotel activated successfully with id={}", id);

        return modelMapper.map(
                updatedHotel,
                HotelResponseDTO.class
        );
    }

    @Override
    @Transactional
    public HotelResponseDTO deactivateHotelById(Long id) {
        log.info("Deactivating the hotel with id: {}", id);

        Hotel hotel = hotelRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Hotel not found with id: " + id)
        );
        if(Boolean.FALSE.equals(hotel.getActive())) {
            throw new HotelAlreadyInactiveException(
                    "Hotel is already inactive with id: " + id
            );
        }
        hotel.setActive(false);
        // TODO: inventory
        Hotel updateHotel = hotelRepository.save(hotel);

        return modelMapper.map(
                updateHotel,
                HotelResponseDTO.class
        );
    }
}
