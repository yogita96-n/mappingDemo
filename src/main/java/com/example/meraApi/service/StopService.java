package com.example.meraApi.service;

import com.example.meraApi.entity.Bus;
import com.example.meraApi.entity.Stop;
import com.example.meraApi.payload.BusDto;
import com.example.meraApi.payload.StopDto;
import com.example.meraApi.repository.StopRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StopService {
    private StopRepository stopRepository;
    public StopService(StopRepository stopRepository){
        this.stopRepository=stopRepository;
    }

    public StopDto addStop(StopDto stopDto) {
        Stop stop = mapToEntity(stopDto);
        Stop savedEntity = stopRepository.save(stop);
        StopDto dto = mapToDto(stop);
        return dto;

    }
    Stop mapToEntity(StopDto stopDto){
        Stop stop=new Stop();
        stop.setId(stopDto.getId());
        stop.setStopName(stopDto.getStopName());
        return stop;
    }
   StopDto mapToDto(Stop stop){
        StopDto stopDto=new StopDto();
        stopDto.setId(stop.getId());
        stopDto.setStopName(stop.getStopName());
        return stopDto;
   }

    public StopDto getDeatilsById(long id) {
        Stop stop = stopRepository.findById(id).orElseThrow(
                () -> new ResolutionException("record is not found with this id:" + id)
        );

        StopDto stopDto = mapToDto(stop);
        return stopDto;

    }

    public List<StopDto> getAlDetails(int pageNo, int pagesize) {
        Pageable page=PageRequest.of(pageNo,pagesize);
        Page<Stop> all = stopRepository.findAll(page);

        List<Stop> stops = all.getContent();
        List<StopDto> dtos = stops.stream().map(s -> mapToDto(s)).collect((Collectors.toList()));
        return dtos;
    }

    public StopDto updateDetails(long id, StopDto stopDto) {

        Stop stop1 = stopRepository.findById(id).get();
        stop1.setId(stopDto.getId());
        stop1.setStopName(stopDto.getStopName());
        Stop savedEntity = stopRepository.save(stop1);
        StopDto stopDto1 = mapToDto(savedEntity);
        return stopDto1;

    }
}
