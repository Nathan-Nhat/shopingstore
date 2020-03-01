package com.ttnhat.shop.Service.AdminService.ProductService;

import com.ttnhat.shop.DAO.NormalDAO.ICategoryRepository;
import com.ttnhat.shop.DAO.NormalDAO.IProductRepository;
import com.ttnhat.shop.Entity.Category;
import com.ttnhat.shop.Entity.ImageDetailProduct;
import com.ttnhat.shop.Entity.Product;
import com.ttnhat.shop.Entity.ProductDate;
import com.ttnhat.shop.Object.UpdateImageDetail;
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
    public Product addSingleProduct(MultipartFile file, Product product, MultipartFile multipartFile1, MultipartFile multipartFile2, MultipartFile multipartFile3,
                                    MultipartFile multipartFile4, MultipartFile multipartFile5, MultipartFile multipartFile6) {
        Date dateNow = new Date();
        Product productTemp = new Product();
        String uniqueID = UUID.randomUUID().toString();
        productTemp.setId(uniqueID);
        productTemp.setName(product.getName());
        productTemp.setPrice(product.getPrice());
        productTemp.setDescription(product.getDescription());
        if(file != null) {
            String filepath = fileStorageService.storeFile(file, product, uniqueID);
            productTemp.setImageMain(filepath);
        }
        productTemp.setLastUpdate(dateNow);
        productTemp.setDateCreate(dateNow);
        productTemp.setCategory(product.getCategory());
        int i = 0;
            ImageDetailProduct imageDetailProduct = new ImageDetailProduct();
            if(multipartFile1 != null) {
                String filepathDetail1 = fileStorageService.storeFile(multipartFile1, product, uniqueID);
                imageDetailProduct.setImageDetails1(filepathDetail1);
            }
            if(multipartFile2 != null) {
                String filepathDetail2 = fileStorageService.storeFile(multipartFile2, product, uniqueID);
                imageDetailProduct.setImageDetails2(filepathDetail2);
            }
            if(multipartFile3 != null) {
                String filepathDetail3 = fileStorageService.storeFile(multipartFile3, product, uniqueID);
                imageDetailProduct.setImageDetails3(filepathDetail3);
            }
            if(multipartFile4 != null) {
                String filepathDetail4 = fileStorageService.storeFile(multipartFile4, product, uniqueID);
                imageDetailProduct.setImageDetails4(filepathDetail4);
            }
            if(multipartFile5 != null) {
                String filepathDetail5 = fileStorageService.storeFile(multipartFile5, product, uniqueID);
                imageDetailProduct.setImageDetails5(filepathDetail5);
            }
            if(multipartFile6 != null) {
                String filepathDetail6 = fileStorageService.storeFile(multipartFile6, product, uniqueID);
                imageDetailProduct.setImageDetails6(filepathDetail6);
            }
            imageDetailProduct.setProduct(productTemp);
            productTemp.setImageDetailProduct(imageDetailProduct);
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
    public Product editProduct(MultipartFile file, Product product, MultipartFile multipartFile1, MultipartFile multipartFile2, MultipartFile multipartFile3,
                               MultipartFile multipartFile4, MultipartFile multipartFile5, MultipartFile multipartFile6) {
        Date dateNow = new Date();
        Product productTemp = new Product();
        productTemp.setId(product.getId());
        productTemp.setName(product.getName());
        productTemp.setPrice(product.getPrice());
        productTemp.setDescription(product.getDescription());
        if(file != null) {
            String filepath = fileStorageService.storeFile(file, product, product.getId());
            productTemp.setImageMain(filepath);
        }
        productTemp.setCategory(product.getCategory());
        int i = 0;
        ImageDetailProduct imageDetailProduct = new ImageDetailProduct();
        if(multipartFile1 != null) {
            String filepathDetail1 = fileStorageService.storeFile(multipartFile1, product, product.getId());
            imageDetailProduct.setImageDetails1(filepathDetail1);
        }
        if(multipartFile2 != null) {
            String filepathDetail2 = fileStorageService.storeFile(multipartFile2, product, product.getId());
            imageDetailProduct.setImageDetails2(filepathDetail2);
        }
        if(multipartFile3 != null) {
            String filepathDetail3 = fileStorageService.storeFile(multipartFile3, product, product.getId());
            imageDetailProduct.setImageDetails3(filepathDetail3);
        }
        if(multipartFile4 != null) {
            String filepathDetail4 = fileStorageService.storeFile(multipartFile4, product, product.getId());
            imageDetailProduct.setImageDetails4(filepathDetail4);
        }
        if(multipartFile5 != null) {
            String filepathDetail5 = fileStorageService.storeFile(multipartFile5, product, product.getId());
            imageDetailProduct.setImageDetails5(filepathDetail5);
        }
        if(multipartFile6 != null) {
            String filepathDetail6 = fileStorageService.storeFile(multipartFile6, product, product.getId());
            imageDetailProduct.setImageDetails6(filepathDetail6);
        }
        imageDetailProduct.setProduct(productTemp);
        productTemp.setImageDetailProduct(imageDetailProduct);
        return productRepository.editProduct(productTemp);
    }

    @Override
    public Product findProductById(String id) {
        return productRepository.findById(id).orElseThrow(()->new RuntimeException("Cannot find Product"));
    }

    @Override
    public void updateImage(UpdateImageDetail updateImageDetail) {
        productRepository.updateImage(updateImageDetail);
    }

    @Override
    public void deleteProductById(String id) {
        productRepository.deleteById(id);
    }

    @Override
    public void addTestProduct(Product product) {
        Date dateNow = new Date();
        Product productTemp = new Product();
        String uniqueID = UUID.randomUUID().toString();
        productTemp.setName(product.getName());
        productTemp.setPrice(product.getPrice());
        productTemp.setId(uniqueID);
        productTemp.setLastUpdate(dateNow);
        productTemp.setDateCreate(dateNow);
        productTemp.setImageMain(product.getImageMain());
        ImageDetailProduct imageDetailProduct = new ImageDetailProduct();
        imageDetailProduct.setImageDetails1(product.getImageDetailProduct().getImageDetails1());
        imageDetailProduct.setImageDetails2(product.getImageDetailProduct().getImageDetails2());
        imageDetailProduct.setImageDetails3(product.getImageDetailProduct().getImageDetails3());
        imageDetailProduct.setImageDetails4(product.getImageDetailProduct().getImageDetails4());
        imageDetailProduct.setImageDetails5(product.getImageDetailProduct().getImageDetails5());
        imageDetailProduct.setImageDetails6(product.getImageDetailProduct().getImageDetails6());
        imageDetailProduct.setProduct(productTemp);
        productTemp.setImageDetailProduct(imageDetailProduct);
        productTemp.setCategory(product.getCategory());
        productRepository.saveTest(productTemp);
    }

    @Override
    public List<ProductDate> getDataByDate(Integer date, String productId) {
        return productRepository.getDataByDate(date, productId);
    }
}
