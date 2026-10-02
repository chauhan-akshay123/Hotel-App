package com.akshay.projects.hotelApp.service.Impl;

import com.akshay.projects.hotelApp.entity.Inventory;
import com.akshay.projects.hotelApp.entity.Room;
import com.akshay.projects.hotelApp.repository.InventoryRepository;
import com.akshay.projects.hotelApp.service.IInventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryServiceImpl implements IInventoryService {

    private final InventoryRepository inventoryRepository;

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
}
