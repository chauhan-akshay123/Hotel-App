package com.akshay.projects.hotelApp.service.Impl;

import com.akshay.projects.hotelApp.dto.Request.HotelSearchRequestDTO;
import com.akshay.projects.hotelApp.dto.Response.HotelResponseDTO;
import com.akshay.projects.hotelApp.entity.Hotel;
import com.akshay.projects.hotelApp.entity.Inventory;
import com.akshay.projects.hotelApp.entity.Room;
import com.akshay.projects.hotelApp.repository.InventoryRepository;
import com.akshay.projects.hotelApp.service.IInventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryServiceImpl implements IInventoryService {

    private final InventoryRepository inventoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public void InitializeRoomForAYear(Room room) {
        LocalDate date = LocalDate.now();
        LocalDate endDate = date.plusYears(1);
        for (; !date.isAfter(endDate); date = date.plusDays(1)) {
            Inventory inventory = Inventory.builder()
                    .hotel(room.getHotel())
                    .room(room)
                    .bookedCount(0)
                    .city(room.getHotel().getCity())
                    .date(date)
                    .price(room.getBasePrice())
                    .surgeFactor(BigDecimal.ONE)
                    .totalCount(room.getTotalCount())
                    .closed(false)
                    .build();
            inventoryRepository.save(inventory);
        }
    }

    @Override
    public void deleteFutureInvetories(Room room) {
      LocalDate today = LocalDate.now();
      inventoryRepository.deleteByDateAfterAndRoom(today, room);
    }

    @Override
    public void deleteInventoriesByRoom(Room room) {
        inventoryRepository.deleteByRoom(room);
    }

    @Override
    public Page<HotelResponseDTO> searchHotels(HotelSearchRequestDTO request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        long dateCount = ChronoUnit.DAYS.between(request.getStartDate(), request.getEndDate()) + 1;
        Page<Hotel> hotelPage = inventoryRepository.findHotelsWithAvailableInventory(request.getCity(), request.getStartDate(), request.getEndDate(), request.getRoomsCount(), dateCount, pageable);
        return hotelPage.map((element) -> modelMapper.map(element, HotelResponseDTO.class));
    }
}
