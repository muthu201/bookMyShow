package com.bookmyshow.bookMyShow.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookmyshow.bookMyShow.Entity.Seat;
import com.bookmyshow.bookMyShow.Service.SeatService;
import com.bookmyshow.bookMyShow.util.ResponseStructure;

import jakarta.validation.Valid;

@RequestMapping("seat")
@RestController
public class SeatController {
	@Autowired
	SeatService sService;
	
	@PostMapping
	ResponseEntity<ResponseStructure<Seat>> saveSeat(@Valid @RequestBody Seat seat,BindingResult result){
		return sService.saveSeat(seat);
	}
	@GetMapping
	ResponseEntity<ResponseStructure<Seat>> findSeat(@RequestParam int seatId){
		return sService.findSeat(seatId);
	}
	@DeleteMapping
	ResponseEntity<ResponseStructure<Seat>> deleteSeat(@RequestParam int seatId){
		return sService.deleteSeat(seatId);
	}
	@PutMapping
	ResponseEntity<ResponseStructure<Seat>> updateSeat(@Valid @RequestBody Seat seat,@RequestParam int seatId,BindingResult result){
		return sService.updateSeat(seat, seatId);
	}
	@GetMapping("findAllSeat")
	ResponseEntity<ResponseStructure<List<Seat>>> findAllSeat(){
		return sService.findAllSeat();
	}
}
