package com.akshay.projects.hotelApp.repository;

import com.akshay.projects.hotelApp.entity.Inventory;
import com.akshay.projects.hotelApp.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    void deleteByDateAfterAndRoom(LocalDate date, Room room);

    void deleteByRoom(Room room);
}
