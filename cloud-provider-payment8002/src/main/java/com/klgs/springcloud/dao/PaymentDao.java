package com.klgs.springcloud.dao;

import com.klgs.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentDao {

    public int create(Payment payment);
    public Payment getPaymentById(Long id);


}
