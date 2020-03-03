package com.ttnhat.shop.DAO.NormalDAO;

import com.ttnhat.shop.Entity.Category;
import com.ttnhat.shop.Entity.Product;
import com.ttnhat.shop.Entity.ProductDate;
import com.ttnhat.shop.ExceptionHandler.Exception.SQLException;
import com.ttnhat.shop.Object.UpdateImageDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

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
            List<Product> productsList = entityManager.createQuery(sql, Product.class)
                                            .getResultList();
            Integer total = productsList.size();
            int page = pageable.getPageNumber();
            int size = pageable.getPageSize();
            if (size > total) size = total;
            List<Product> pagedListProduct = new ArrayList<>(productsList.subList(page * size, (page+1)*size ));
            Page<Product> pageProduct = new PageImpl<Product>(pagedListProduct, pageable, total);
            entityManager.getTransaction().commit();
            entityManager.close();
            return pageProduct;
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
            LocalDate date = LocalDate.now();
            String sql = "select u from ProductDate u where u.product.id = :id and u.date = :date";
            List<ProductDate> productDates = entityManager.createQuery(sql, ProductDate.class)
                                            .setParameter("id", id)
                                            .setParameter("date", date).getResultList();
            Product product1 = entityManager.find(Product.class, id);
            System.out.println(product1.getCategory());
            System.out.println(product1.getImageDetailProduct());
            if(productDates.isEmpty())
            {
                ProductDate productDate = new ProductDate();
                productDate.setId(0);
                productDate.setCount(0);
                productDate.setProduct(product1);
                productDate.setDate(date);
                entityManager.merge(productDate);
            } else {
                Integer count = productDates.get(0).getCount();
                count++;
                productDates.get(0).setCount(count);
            }
            entityManager.flush();

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
            System.out.println(product);
            entityManager.getTransaction().begin();
            Product tempProduct = entityManager.find(Product.class, product.getId());
            tempProduct.setName(product.getName());
            tempProduct.setDescription(product.getDescription());
            tempProduct.setPrice(product.getPrice());
            if (product.getLastUpdate() != null) {
                tempProduct.setLastUpdate(tempProduct.getLastUpdate());
            }
            if (product.getImageMain() != null){
                tempProduct.setImageMain(product.getImageMain());
            }
            if (product.getImageDetailProduct().getImageDetails1() != null){
                tempProduct.getImageDetailProduct().setImageDetails1(product.getImageDetailProduct().getImageDetails1());
            }
            if (product.getImageDetailProduct().getImageDetails2() != null){
                tempProduct.getImageDetailProduct().setImageDetails2(product.getImageDetailProduct().getImageDetails2());
            }
            if (product.getImageDetailProduct().getImageDetails3() != null){
                tempProduct.getImageDetailProduct().setImageDetails3(product.getImageDetailProduct().getImageDetails3());
            }
            if (product.getImageDetailProduct().getImageDetails4() != null){
                tempProduct.getImageDetailProduct().setImageDetails4(product.getImageDetailProduct().getImageDetails4());
            }
            if (product.getImageDetailProduct().getImageDetails5() != null){
                tempProduct.getImageDetailProduct().setImageDetails5(product.getImageDetailProduct().getImageDetails5());
            }
            if (product.getImageDetailProduct().getImageDetails6() != null){
                tempProduct.getImageDetailProduct().setImageDetails1(product.getImageDetailProduct().getImageDetails6());
            }
            entityManager.getTransaction().commit();
            entityManager.close();
            return product;
        } catch (RuntimeException e){
            entityManager.getTransaction().rollback();
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public void updateImage(UpdateImageDetail updateImageDetail) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            Product product = entityManager.find(Product.class, updateImageDetail.getId());
            if (!updateImageDetail.getImage0())
            {
                product.setImageMain(null);
            }
            if (!updateImageDetail.getImage1())
            {
                product.getImageDetailProduct().setImageDetails1(null);
            }
            if (!updateImageDetail.getImage2())
            {
                product.getImageDetailProduct().setImageDetails2(null);
            }
            if (!updateImageDetail.getImage3())
            {
                product.getImageDetailProduct().setImageDetails3(null);
            }
            if (!updateImageDetail.getImage4())
            {
                product.getImageDetailProduct().setImageDetails4(null);
            }
            if (!updateImageDetail.getImage5())
            {
                product.getImageDetailProduct().setImageDetails5(null);
            }
            if (!updateImageDetail.getImage6())
            {
                product.getImageDetailProduct().setImageDetails6(null);
            }
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (RuntimeException e){
            entityManager.getTransaction().rollback();
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public Page<Product> getProductByName(Pageable pageable, String name, String category) {
        EntityManager entityManager = getEntityManager();
        String stringSearch = "%"+name+"%";
        try{
            entityManager.getTransaction().begin();
            String sql = "select u from Product u where u.name like :name and u.category.name = :category";
            List<Product> products = entityManager.createQuery(sql, Product.class)
                    .setParameter("name", stringSearch)
                    .setParameter("category", category)
                    .getResultList();
            Integer total = products.size();
            int page = pageable.getPageNumber();
            int size = pageable.getPageSize();
            if (size > total) size = total;
            List<Product> pagedListProduct = new ArrayList<>(products.subList(page * size, (page+1)*size ));
            Page<Product> pageProduct = new PageImpl<Product>(pagedListProduct, pageable, total);
            System.out.println(pageable);
            System.out.println(total);
            entityManager.getTransaction().commit();
            entityManager.close();
            return pageProduct;
        } catch (RuntimeException e){
            entityManager.getTransaction().rollback();
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public void deleteById(String id) {
        EntityManager entityManager = getEntityManager();
        try{
            entityManager.getTransaction().begin();
            Product product = entityManager.find(Product.class, id);
            entityManager.remove(product);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (RuntimeException e){
            entityManager.getTransaction().rollback();
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public void saveTest(Product product) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(product);
            Category category = entityManager.find(Category.class, product.getCategory().getId());
            product.setCategory(category);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (RuntimeException e){
            entityManager.getTransaction().rollback();
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public List<ProductDate> getDataByDate(Integer date, String productId) {
        EntityManager entityManager = getEntityManager();
        try{
            entityManager.getTransaction().begin();
            LocalDate now = LocalDate.now();
            Timestamp timestamp = Timestamp.valueOf(now.atStartOfDay());
            long time = date * 24 * 60 * 60 * 1000;
            Timestamp timestampTemp = new Timestamp(timestamp.getTime() + time);
            LocalDate localDateTemp = timestampTemp.toLocalDateTime().toLocalDate();
            String sql = "select u from ProductDate u where u.product.id = :productId and u.date < :date";
            List<ProductDate> productDates = entityManager.createQuery(sql, ProductDate.class)
                    .setParameter("productId", productId)
                    .setParameter("date", localDateTemp)
                    .getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
            return productDates;
        } catch (RuntimeException e){
            entityManager.getTransaction().rollback();
            throw new SQLException(e.getMessage());
        }
    }
}
