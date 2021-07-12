package com.app.utils;

import com.app.dto.OrderDto;
import com.app.dto.UserDto;
import com.app.entity.Order;
import com.app.entity.OrderDetail;
import com.app.entity.Shop;
import com.app.entity.User;
import com.app.service.orderdetailservice.IOrderDetailService;
import com.app.service.shopservice.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class AppMapper {
    @Autowired
    private  IShopService shopService;

    @Autowired
    private IOrderDetailService orderDetailService;

    public  UserDto userAppToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setUserName(user.getUserName());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        if(user.getAddress()==null){
            userDto.setAddress("không xác định");
        }
        else {
            userDto.setAddress(user.getAddress());
        }
        List<Shop> shopList = (List<Shop>) shopService.finAllByUser(user);
        if(shopList.isEmpty()){
            userDto.setShopName("chưa có shop");
        }
        else {
            userDto.setShopName(shopList.get(0).getName());
        }
        if(user.isStatus()){
            userDto.setStatus("đang hoạt động");
        }
        else {
            userDto.setStatus("đã khoá");
        }
        if(user.getPhoneNumber()==null){
            userDto.setPhoneNumber("không xác định");
        }
        else {
            userDto.setPhoneNumber(user.getPhoneNumber());
        }
        return userDto;
    }

    public OrderDto orderToOrderDTO(Order order){
        List<OrderDetail> orderDetails = (List<OrderDetail>) orderDetailService.findAllByOrder(order);
        OrderDto orderDto = new OrderDto();
        orderDto.setAddress(order.getAddress());
        orderDto.setCreateTime(order.getCreateTime());
        orderDto.setOrderDetailList(orderDetails);
        orderDto.setId(order.getId());
        orderDto.setStatus(order.getStatus());
        if (order.getDescription()==null){
            orderDto.setDescription("không có nhắc nhở");
        }
        else {
            orderDto.setDescription(order.getDescription());
        }
        orderDto.setEndTime(order.getEndTime());
        return orderDto;
    }


}
