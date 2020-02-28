package com.ttnhat.shop.Service.AdminService.ProductService;

import com.ttnhat.shop.DAO.NormalDAO.ICategoryRepository;
import com.ttnhat.shop.DAO.NormalDAO.IProductRepository;
import com.ttnhat.shop.Entity.Category;
import com.ttnhat.shop.Entity.ImageDetailProduct;
import com.ttnhat.shop.Entity.Product;
import com.ttnhat.shop.Service.AdminService.FileStorageService.FileStorageService;
import com.ttnhat.shop.Service.AdminService.FileStorageService.IFileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class AdminProductService implements IAdminProductService {
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private IFileStorageService fileStorageService;
    @Autowired
    private ICategoryRepository categoryRepository;
    @Override
    public Product addSingleProduct(MultipartFile file, Product product, MultipartFile[] multipartFiles) {
        Date dateNow = new Date();
        Product productTemp = new Product();
        String uniqueID = UUID.randomUUID().toString();
        productTemp.setId(uniqueID);
        productTemp.setName(product.getName());
        productTemp.setPrice(product.getPrice());
        productTemp.setDescription(product.getDescription());
        if(file != null) {
            String filepath = fileStorageService.storeFile(file, product, uniqueID, "content");
            productTemp.setImageMain(filepath);
        }
        productTemp.setLastUpdate(dateNow);
        productTemp.setDateCreate(dateNow);
        productTemp.setNumClick(0);
        productTemp.setNumOrdered(0);
        productTemp.setCategory(product.getCategory());
        int i = 0;
        if(multipartFiles != null) {
            List<ImageDetailProduct> imageDetailProducts = new ArrayList<>();
                for (MultipartFile detailFile : multipartFiles) {
                    String filepathDetail = fileStorageService.storeFile(detailFile, product, uniqueID, "detail-" + i);
                    ImageDetailProduct imageDetailProduct = new ImageDetailProduct(filepathDetail, i);
                    imageDetailProduct.setProduct(productTemp);
                    imageDetailProducts.add(imageDetailProduct);
                    i++;
                }
                productTemp.setImageDetailProduct(imageDetailProducts);
            }
        return productRepository.save(productTemp).orElseThrow(()->new RuntimeException("Cannot save product"));
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Page<Product> getAllUProductByPage(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> listProduct = productRepository.findAllProduct(pageable);
        return listProduct;
    }

    @Override
    public Product editProduct(MultipartFile file, Product product, MultipartFile[] multipartFiles) {
        Product tempProduct = new Product();
        Date date = new Date();
        tempProduct.setName(product.getName());
        tempProduct.setPrice(product.getPrice());
        tempProduct.setDescription(product.getDescription());
        tempProduct.setLastUpdate(date);
        if(file != null) {
            String filepath = fileStorageService.storeFile(file, product, product.getId(), "content");
            tempProduct.setImageMain(filepath);
        }
        int i = 0;
        if(multipartFiles != null) {
            List<ImageDetailProduct> imageDetailProducts = new ArrayList<>();
            String filepathDetail = null;
            ImageDetailProduct imageDetailProduct = null;
            for (MultipartFile detailFile : multipartFiles) {
                if(detailFile != null) {
                    filepathDetail = fileStorageService.storeFile(detailFile, product, product.getId(), "detail-" + i);
                    imageDetailProduct = new ImageDetailProduct(filepathDetail, i);
                    imageDetailProduct.setProduct(tempProduct);
                }
                imageDetailProducts.add(imageDetailProduct);
                i++;
            }
            tempProduct.setImageDetailProduct(imageDetailProducts);
        }
        return productRepository.editProduct(product);
    }

    @Override
    public Product findProductById(String id) {
        return productRepository.findById(id).orElseThrow(()->new RuntimeException("Cannot find Product"));
    }
}
