package com.akshay.projects.hotelApp.repository;

import com.akshay.projects.hotelApp.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    boolean existsByNameIgnoreCaseAndCityIgnoreCase(
            String name,
            String city
    );
}
