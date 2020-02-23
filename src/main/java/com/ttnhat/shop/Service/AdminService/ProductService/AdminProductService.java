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
        Product productOptional = null;

        if (!productRepository.findById(uniqueID).isPresent()) {
            productTemp.setId(uniqueID);
            productTemp.setName(product.getName());
            productTemp.setPrice(product.getPrice());
            Category category = categoryRepository.findById(product.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("cannot find category"));
            productTemp.setCategory(category);
            productTemp.setDescription(product.getDescription());
            if(file != null) {
                String filepath = fileStorageService.storeFile(file, productTemp, "content");
                productTemp.setImageMain(filepath);
            }
            productTemp.setLastUpdate(dateNow);
            productTemp.setDateCreate(dateNow);
            productTemp.setNumClick(0);
            productTemp.setNumOrdered(0);
            int i = 0;
            if(multipartFiles != null) {
                List<ImageDetailProduct> imageDetailProducts = new ArrayList<>();
                for (MultipartFile detailFile : multipartFiles) {
                    String filepathDetail = fileStorageService.storeFile(detailFile, productTemp, "detail-" + i);
                    ImageDetailProduct imageDetailProduct = new ImageDetailProduct(filepathDetail);
                    imageDetailProduct.setProduct(productTemp);
                    imageDetailProducts.add(imageDetailProduct);
                    i++;
                }
                productTemp.setImageDetailProduct(imageDetailProducts);
            }
            productOptional = productRepository.saveAndFlush(productTemp);
        }
        else
        {
            throw new RuntimeException("Product already existed");
        }
        return productOptional;
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Page<Product> getAllUserByPage(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> listProduct = productRepository.findAll(pageable);
        return listProduct;
    }
}
