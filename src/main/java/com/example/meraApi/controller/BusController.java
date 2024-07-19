package com.example.meraApi.controller;

import com.example.meraApi.entity.Bus;
import com.example.meraApi.repository.BusRepository;
import com.example.meraApi.service.BusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bus")
public class BusController {
    private BusService busService;

    public BusController(BusService busService) {
        this.busService = busService;
    }
    @PostMapping
    public ResponseEntity<Bus>addDeatils(@RequestBody Bus bus){
        Bus saved = busService.addBus(bus);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<Bus>getBusById(@RequestParam long id){
        Bus busById = busService.getBusById(id);
        return new ResponseEntity<>(busById,HttpStatus.OK);
    }
    @GetMapping("/byId")
    public ResponseEntity<List<Bus>>getAllDetails(
            @RequestParam (name="pageNo", defaultValue ="0",required = false)int pageNo,
            @RequestParam(name="pageSize",defaultValue = "3",required = false)int pageSize

    ){
        List<Bus> buses = busService.getAllDetails(pageNo,pageSize);
        return new ResponseEntity<>(buses,HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<Bus>updateDetails(@RequestParam long id,@RequestBody Bus bus){
        Bus bus1 = busService.updateDetails(id, bus);
        return new ResponseEntity<>(bus1,HttpStatus.OK);
    }
   @DeleteMapping
public ResponseEntity<String>deleteDetails(@RequestParam long id){
      busService.deleteDetailsById(id);
        return new ResponseEntity<>("RECORD DELETED", HttpStatus.OK);
}
}

