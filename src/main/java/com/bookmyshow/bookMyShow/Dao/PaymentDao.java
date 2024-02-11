package com.bookmyshow.bookMyShow.Dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookmyshow.bookMyShow.Entity.Payment;
import com.bookmyshow.bookMyShow.Repo.PaymentRepo;

@Repository
public class PaymentDao {
	@Autowired
	PaymentRepo pRepo;
	
	public Payment savePayment(Payment payment) {
		return pRepo.save(payment);
	}
	public Payment findPayment(int paymentId) {
		Optional<Payment> opPayment=pRepo.findById(paymentId);
		if(opPayment.isPresent()) {
			return opPayment.get();
		}
		return null;
	}
	public Payment deletePayment(int paymentId) {
		Payment payment=findPayment(paymentId);
		pRepo.delete(payment);
		return payment;
	}
	public Payment updatePayment(Payment payment,int paymentId) {
		Payment newPayment=findPayment(paymentId);
		if(newPayment != null) {
			payment.setPaymentId(paymentId);
			return pRepo.save(payment);
		}
		return null;
	}
	

}
