package com.klgs.springcloud.service.impl;

import com.klgs.springcloud.dao.OrderDao;
import com.klgs.springcloud.pojo.Order;
import com.klgs.springcloud.service.AccountService;
import com.klgs.springcloud.service.OrderService;
import com.klgs.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private StorageService storageService;

    @Resource
    private AccountService accountService;

    @Override
    @GlobalTransactional(name = "fw_tx_group", rollbackFor = Exception.class)
    public void create(Order order) {
        //新建订单
        log.info("开始新建订单");
        orderDao.create(order);
        log.info("新建订单完成");
        //调用库存模块
        log.info("开始调用库存模块");
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("结束调用库存模块");
        //调用账户模块
        log.info("开始调用账户模块");
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("结束调用账户模块");
        //修改订单状态
        log.info("开始修改订单");
        orderDao.update(order.getUserId(), 0);
        log.info("结束修改订单");
        log.info("订单结束");
    }
}
