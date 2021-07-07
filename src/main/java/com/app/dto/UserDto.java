package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String name;
    private String userName;
    private String email;
    private String address;
    private String phoneNumber;
    private String shopName;
    private String status;

}
