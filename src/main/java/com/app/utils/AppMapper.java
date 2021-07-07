package com.app.utils;

import com.app.dto.UserDto;
import com.app.entity.Shop;
import com.app.entity.User;
import com.app.service.shopservice.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class AppMapper {
    @Autowired
    private  IShopService shopService;

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
}
