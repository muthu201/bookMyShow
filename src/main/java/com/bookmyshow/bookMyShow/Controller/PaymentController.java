package com.bookmyshow.bookMyShow.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookmyshow.bookMyShow.Entity.Payment;
import com.bookmyshow.bookMyShow.Service.PaymentService;
import com.bookmyshow.bookMyShow.util.ResponseStructure;

@RequestMapping("payment")
@RestController
public class PaymentController {
	@Autowired
	PaymentService pService;
	@PostMapping
	ResponseEntity<ResponseStructure<Payment>> savePayment(@RequestBody Payment payment){
		return pService.savePayment(payment);
	}
	
	@GetMapping
	ResponseEntity<ResponseStructure<Payment>> findPayment(@RequestParam int paymentId){
		return pService.findPayment(paymentId);
	}
	@DeleteMapping
	ResponseEntity<ResponseStructure<Payment>> deletePayment(@RequestParam int paymentId){
		return pService.deletePayment(paymentId);
	}
	@PutMapping
	ResponseEntity<ResponseStructure<Payment>> updatePayment(@RequestBody Payment payment,@RequestParam int paymentId){
		return pService.updatePayment(payment, paymentId);
	}
	
}
