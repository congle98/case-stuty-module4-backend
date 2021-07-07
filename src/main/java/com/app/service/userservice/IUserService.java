package com.app.service.userservice;

import com.app.entity.User;
import com.app.service.IGeneralService;

import java.util.Optional;

public interface IUserService extends IGeneralService<User> {
    Optional<User> findByUserName(String userName);
}
