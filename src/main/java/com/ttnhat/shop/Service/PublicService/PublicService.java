package com.ttnhat.shop.Service.PublicService;

import com.ttnhat.shop.DAO.NormalDAO.IProductRepository;
import com.ttnhat.shop.Entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PublicService implements IPublicService{
    @Autowired
    private IProductRepository productRepository;
    @Override
    public Page<Product> getAllUProductByPage(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> listProduct = productRepository.findAllProduct(pageable);
        return listProduct;
    }

    @Override
    public Product findProductById(String id) {
        return productRepository.findById(id).orElseThrow(()->new RuntimeException("Cannot find Product"));
    }

    @Override
    public Page<Product> getProductBySearchName(String name, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        if (name != null) {
            return productRepository.getProductByName(pageable, name);
        } else {
            return productRepository.findAllProduct(pageable);
        }
    }
}