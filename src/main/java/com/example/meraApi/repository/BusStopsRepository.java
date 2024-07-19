package com.example.meraApi.repository;

import com.example.meraApi.entity.BusStops;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusStopsRepository extends JpaRepository<BusStops, Long> {
}