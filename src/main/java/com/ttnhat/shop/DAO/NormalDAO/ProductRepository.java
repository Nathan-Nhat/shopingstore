package com.ttnhat.shop.DAO.NormalDAO;

import com.ttnhat.shop.Entity.Category;
import com.ttnhat.shop.Entity.ImageDetailProduct;
import com.ttnhat.shop.Entity.Product;
import com.ttnhat.shop.ExceptionHandler.Exception.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProductRepository implements IProductRepository {
    @Autowired
    private EntityManagerFactory emf;

    private EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    @Override
    public Page<Product> findAllProduct(Pageable pageable) {
        EntityManager entityManager = getEntityManager();
        Page<Product> products = null;
        try {
            entityManager.getTransaction().begin();
            String sql = "select u from Product u";
            String sqlCount = "select count(u) from Product u";
            List<Product> productsList = entityManager.createQuery(sql, Product.class)
                                            .getResultList();
            Long total = entityManager.createQuery(sqlCount, Long.class).getSingleResult();
            products = new PageImpl<Product>(productsList, pageable, total);
            entityManager.getTransaction().commit();
            entityManager.close();
            return products;
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
            entityManager.getTransaction().rollback();
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public Optional<Product> findById(String id) {
        EntityManager entityManager = getEntityManager();
        Optional<Product> product = null;
        try{
            entityManager.getTransaction().begin();
            Product product1 = entityManager.find(Product.class, id);
            System.out.println(product1.getCategory());
            System.out.println(product1.getImageDetailProduct());
            product = Optional.ofNullable(product1);
            entityManager.getTransaction().commit();
            entityManager.close();
            return product;
        } catch (RuntimeException e){
            entityManager.getTransaction().rollback();
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public Optional<Product> save(Product product) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(product);
            Category category = entityManager.find(Category.class, product.getCategory().getId());
            product.setCategory(category);
            entityManager.getTransaction().commit();
            entityManager.close();
            return Optional.ofNullable(product);
        } catch (RuntimeException e){
            entityManager.getTransaction().rollback();
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public Product editProduct(Product product) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Product tempProduct = entityManager.find(Product.class, product.getId());
            tempProduct.setName(product.getName());
            tempProduct.setDescription(product.getDescription());
            tempProduct.setPrice(product.getPrice());
            tempProduct.setLastUpdate(tempProduct.getLastUpdate());
            tempProduct.setImageMain(product.getImageMain());
            entityManager.flush();
            System.out.println(product.getImageDetailProduct());
//            for (ImageDetailProduct image : product.getImageDetailProduct())
//            {
//                if (image != null) {
//                    System.out.println(image);
//                    String sql = "update ImageDetailProduct u set u.imageDetails = :image_detail";
//                    entityManager.createQuery(sql)
//                            .setParameter("image_detail", image.getImageDetails())
//                            .executeUpdate();
//                }
//            }
            entityManager.getTransaction().commit();
            entityManager.close();
            return product;
        } catch (RuntimeException e){
            entityManager.getTransaction().rollback();
            throw new SQLException(e.getMessage());
        }
    }
}
