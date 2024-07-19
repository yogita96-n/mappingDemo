package com.example.meraApi.controller;

import com.example.meraApi.entity.Stop;
import com.example.meraApi.payload.StopDto;
import com.example.meraApi.repository.StopRepository;
import com.example.meraApi.service.StopService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stops")
public class StopController {
    private StopService stopService;
    public StopController(StopService stopService){
        this.stopService=stopService;
    }
    @PostMapping
    public ResponseEntity<StopDto>addStops(@RequestBody StopDto stopDto ){
        StopDto saveEntity = stopService.addStop(stopDto);
        return new ResponseEntity<>(saveEntity, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<StopDto>getDetailsById(@RequestParam long id){
        StopDto deatilsById = stopService.getDeatilsById(id);
        return new ResponseEntity<>(deatilsById,HttpStatus.OK);
    }
@GetMapping("/byId")
    public ResponseEntity<List<StopDto>>getAllDetails(
            @RequestParam(name="pageNo",defaultValue = "0",required = false)int pageNo,
            @RequestParam(name="pageSize",defaultValue = "3",required = false)int pagesize
            ){
    List<StopDto> details = stopService.getAlDetails(pageNo, pagesize);
    return new ResponseEntity<>(details,HttpStatus.OK);
}

@PutMapping
    public ResponseEntity<StopDto>updateDetailsById(@RequestParam long id, @RequestBody StopDto stopDto){
    StopDto stopDto1 = stopService.updateDetails(id, stopDto);
    return new ResponseEntity<>(stopDto1,HttpStatus.OK);
}




}

