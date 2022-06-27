package com.klgs.springcloud.service;

import com.klgs.springcloud.entities.Payment;

public interface PaymentService {

    public int create(Payment payment);
    public Payment getPaymentById(Long id);
}
