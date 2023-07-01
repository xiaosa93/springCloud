package cn.itcast.order.service;

import cn.itcast.feign.clients.UserClient;
import cn.itcast.feign.pojo.User;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private UserClient userClient;
    @Autowired
    private OrderMapper orderMapper;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        //用Feign调用
        User user = userClient.findById(order.getUserId());
        order.setUser(user);
        // 4.返回
        return order;
    }

//    public Order queryOrderById(Long orderId) {
//        // 1.查询订单
//        Order order = orderMapper.findById(orderId);
//        //2.利用restTemplate发起http请求
//        String url = "http://userService/user/"+order.getUserId();
//        User user = restTemplate.getForObject(url, User.class);
//        //3.封装user到order
//        order.setUser(user);
//        // 4.返回
//        return order;
//    }
}
