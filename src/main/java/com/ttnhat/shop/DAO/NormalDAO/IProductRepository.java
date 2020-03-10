package com.ttnhat.shop.DAO.NormalDAO;

import com.ttnhat.shop.Entity.Product;
import com.ttnhat.shop.Entity.ProductDate;
import com.ttnhat.shop.Object.UpdateImageDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface IProductRepository{
    Optional<Product> findById(String id);
    Optional<Product> save(Product product);
    Product editProduct(Product product);
    void updateImage(UpdateImageDetail updateImageDetail);
    Page<Product> getProductByName(Pageable pageable, String name, Integer categoryId, String typeSort);
    void deleteById(String id);
    void saveTest(Product product);
    List<ProductDate> getDataByDate(Integer date, String productId);
}
