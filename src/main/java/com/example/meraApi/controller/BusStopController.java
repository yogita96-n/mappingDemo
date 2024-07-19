package com.example.meraApi.controller;

import com.example.meraApi.entity.Bus;
import com.example.meraApi.entity.BusStops;
import com.example.meraApi.entity.Stop;
import com.example.meraApi.repository.BusRepository;
import com.example.meraApi.repository.BusStopsRepository;
import com.example.meraApi.repository.StopRepository;
import org.springframework.aot.hint.annotation.Reflective;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/busStops")
public class BusStopController {
    @Autowired
    private BusRepository busRepository;
    @Autowired
    private StopRepository stopRepository;

    private BusStopsRepository busStopsRepository;
    public BusStopController(BusStopsRepository busStopsRepository){
        this.busStopsRepository=busStopsRepository;
    }
@PostMapping
    public ResponseEntity<BusStops>addBusStops(
            @RequestBody BusStops busStops,
            @RequestParam long busId,@RequestParam long stopId){
    Bus bus = busRepository.findById(busId).get();
    Stop stop = stopRepository.findById(stopId).get();
    busStops.setBus(bus);
    busStops.setStop(stop);
    BusStops saveEntity = busStopsRepository.save(busStops);
    return new ResponseEntity<>(saveEntity, HttpStatus.CREATED);
}
}

