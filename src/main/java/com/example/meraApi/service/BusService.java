package com.example.meraApi.service;

import com.example.meraApi.entity.Bus;
import com.example.meraApi.payload.BusDto;
import com.example.meraApi.repository.BusRepository;
import com.example.meraApi.repository.BusStopsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;

@Service
public class BusService {
    private BusRepository busRepository;
    private final BusStopsRepository busStopsRepository;

    public BusService(BusRepository busRepository,
                      BusStopsRepository busStopsRepository) {
        this.busRepository = busRepository;
        this.busStopsRepository = busStopsRepository;
    }

    public Bus addBus(Bus bus) {
        Bus bus1 = busRepository.save(bus);
        return bus1;
    }
    Bus mapTOEntity(BusDto busDto){
        Bus b1= new Bus();
        b1.setId(busDto.getId());
        b1.setBusName(busDto.getBusName());


        return b1;
    }
    BusDto mapTODto(Bus bus){
        BusDto dto= new BusDto();
        dto.setId(bus.getId());
        dto.setBusName(bus.getBusName());


        return dto;
    }

    public Bus getBusById(long id) {
        Bus bus = busRepository.findById(id).orElseThrow(
                () -> new ResolutionException("Id is invalid with id:" + id)
        );


        return bus;
    }

    public List<Bus> getAllDetails(int pageNo, int pageSize) {
        Pageable page = PageRequest.of(pageNo, pageSize);
        Page<Bus> busPage = busRepository.findAll(page);
        List<Bus> buses = busPage.getContent();
        return buses;
    }

    public Bus updateDetails(long id, Bus bus) {
        Optional<Bus> byId = busRepository.findById(id);
        Bus bus1 = byId.get();
        bus1.setBusName("New Bus Name"); // Fix 1
        Bus savedEntity = busRepository.save(bus1);
        return savedEntity;
    }


    public void deleteDetailsById(long id) {
        busRepository.deleteById(id);
    }
}
