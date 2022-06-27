package com.klgs.springcloud.dao;

import com.klgs.springcloud.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {

    public void create(Order order);

    public void update(@Param("userId") Long userId, @Param("status") Integer status);
}
