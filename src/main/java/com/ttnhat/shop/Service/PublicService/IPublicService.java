package com.ttnhat.shop.Service.PublicService;

import com.ttnhat.shop.Entity.Product;
import com.ttnhat.shop.Sercurity.Entity.UsersEntity;
import org.springframework.data.domain.Page;

public interface IPublicService {
    Product findProductById(String id);
    Page<Product> getProductBySearchName(String name, Integer page, Integer size, String category, String types, String sorts);
    UsersEntity signUpUser(UsersEntity usersEntity);
}
