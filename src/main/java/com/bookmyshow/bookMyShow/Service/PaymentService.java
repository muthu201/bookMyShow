package com.bookmyshow.bookMyShow.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.bookmyshow.bookMyShow.Dao.PaymentDao;
import com.bookmyshow.bookMyShow.Entity.Payment;
import com.bookmyshow.bookMyShow.Exception.PaymentNotFound;
import com.bookmyshow.bookMyShow.util.ResponseStructure;
@Service
public class PaymentService {
	@Autowired
	PaymentDao pDao;
	public ResponseEntity<ResponseStructure<Payment>> savePayment(Payment payment) {
		ResponseStructure<Payment> structure=new ResponseStructure<Payment>();
		structure.setMessage("Payment save success");
		structure.setStatus(HttpStatus .CREATED.value());
		structure.setData(pDao.savePayment(payment));
		return new ResponseEntity<ResponseStructure<Payment>>(structure,HttpStatus.CREATED);
	}
	public ResponseEntity<ResponseStructure<Payment>> findPayment(int paymentId) {
		ResponseStructure<Payment> structure=new ResponseStructure<Payment>();
		Payment payment=pDao.findPayment(paymentId);
		if(payment != null) {
		structure.setMessage("Payment found success");
		structure.setStatus(HttpStatus .FOUND.value());
		structure.setData(payment);
		return new ResponseEntity<ResponseStructure<Payment>>(structure,HttpStatus.FOUND);
	}
		throw new PaymentNotFound("payment not found for the given id");

	}
	public ResponseEntity<ResponseStructure<Payment>> deletePayment(int paymentId) {
		ResponseStructure<Payment> structure=new ResponseStructure<Payment>();
		Payment payment=pDao.findPayment(paymentId);
		if(payment != null) {
		structure.setMessage("Payment Delete success");
		structure.setStatus(HttpStatus .OK.value());
		structure.setData(pDao.deletePayment(paymentId));
		return new ResponseEntity<ResponseStructure<Payment>>(structure,HttpStatus.OK);
	}
		throw new PaymentNotFound("payment not deleted because,payment not found for the given id");
	}
	public ResponseEntity<ResponseStructure<Payment>> updatePayment(Payment payment,int paymentId) {
		ResponseStructure<Payment> structure=new ResponseStructure<Payment>();
		Payment exPayment=pDao.updatePayment(payment, paymentId);
		if(exPayment != null) {
		structure.setMessage("Payment update success");
		structure.setStatus(HttpStatus .OK.value());
		structure.setData(exPayment);
		return new ResponseEntity<ResponseStructure<Payment>>(structure,HttpStatus.OK);
	}
		throw new PaymentNotFound("payment not updated because,payment not found for the given id");
	}
}
