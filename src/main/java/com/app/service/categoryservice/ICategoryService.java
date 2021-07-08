package com.app.service.categoryservice;

import com.app.entity.Category;
import com.app.service.IGeneralService;

import java.util.Optional;

public interface ICategoryService extends IGeneralService<Category> {
    Optional<Category> findByName(String name);
}
