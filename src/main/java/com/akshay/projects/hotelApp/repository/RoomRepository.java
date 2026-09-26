package com.akshay.projects.hotelApp.repository;

import com.akshay.projects.hotelApp.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByHotelId(Long hotelId);

    boolean existsByHotelIdAndTypeIgnoreCase(
            Long hotelId,
            String type
    );
}
