package com.food.ordering.system.order.service.domain;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.food.ordering.system.order.service.domain.dto.message.PaymentResponse;
import com.food.ordering.system.order.service.domain.ports.input.message.listener.payment.PaymentResponseMessageListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@Service
public class PaymentResponseMessageListenerImp implements PaymentResponseMessageListener {

	@Override
	public void paymentCompleted(PaymentResponse paymentResponse) {
		// TODO Auto-generated method stub

	}

	@Override
	public void paymentCancelled(PaymentResponse paymentResponse) {
		// TODO Auto-generated method stub

	}

}
