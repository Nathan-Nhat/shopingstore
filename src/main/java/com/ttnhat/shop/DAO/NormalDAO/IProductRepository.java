package com.ttnhat.shop.DAO.NormalDAO;

import com.ttnhat.shop.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product, String> {
    Page<Product> findAll(Pageable pageable);
}
