package com.boot;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boot.dao.OrderMapper;
import com.boot.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Test02 {

    @Autowired
    private OrderMapper orderMapper;

    //根据inline策略进行分表，但是不分库
    @Test
    void addOrderByinlineShardingTable(){

        for (int i = 0; i < 10; i++) {

            Order order = new Order();

            //orderid不需要自己手动插入了！
            order.setOrderInfo("订单信息"+(i+1))
                    .setUserId(Long.parseLong(""+(1001+i)));

            orderMapper.insert(order);
        }

    }

    //查询全部数据
    @Test
    void selectAllByinlineShardingTable(){

        List<Order> orders = orderMapper.selectList(null);

        orders.forEach(System.out::println);

    }

    //查询指定order_id的数据
    @Test
    void selectOrderByinlineShardingTable_orderid(){

        QueryWrapper<Order> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("order_id",762459329767931905L);
        List<Order> orders = orderMapper.selectList(objectQueryWrapper);

        orders.forEach(System.out::println);

    }


}
