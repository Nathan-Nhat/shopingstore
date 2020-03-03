package com.ttnhat.shop.Service.PublicService;

import com.ttnhat.shop.Entity.Product;
import org.springframework.data.domain.Page;

public interface IPublicService {
    Page<Product> getAllUProductByPage(Integer page, Integer size);
    Product findProductById(String id);
    Page<Product> getProductBySearchName(String name, Integer page, Integer size, String category);
}
