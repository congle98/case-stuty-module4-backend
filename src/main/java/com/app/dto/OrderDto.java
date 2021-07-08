package com.app.dto;

import com.app.entity.OrderDetail;
import com.app.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private String description;
    private String address;
    private Date createTime;
    private Date endTime;
    private String status;
    private List<OrderDetail> orderDetailList;
    private User user;

}

