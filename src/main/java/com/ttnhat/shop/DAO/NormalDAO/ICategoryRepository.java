package com.ttnhat.shop.DAO.NormalDAO;

import com.ttnhat.shop.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findById(Integer id);
}
