package com.akshay.projects.hotelApp.service;

import com.akshay.projects.hotelApp.entity.Room;

public interface IInventoryService {

    void InitializeRoomForAYear(Room room);

    void deleteFutureInvetories(Room room);

    void deleteInventoriesByRoom(Room room);
}
