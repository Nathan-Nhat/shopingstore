package com.ttnhat.shop.Service.SecuredService.ProductService;

import com.ttnhat.shop.DAO.NormalDAO.ICategoryRepository;
import com.ttnhat.shop.DAO.NormalDAO.IProductRepository;
import com.ttnhat.shop.Entity.Category;
import com.ttnhat.shop.Entity.ImageDetailProduct;
import com.ttnhat.shop.Entity.Product;
import com.ttnhat.shop.Entity.ProductDate;
import com.ttnhat.shop.Object.UpdateImageDetail;
import com.ttnhat.shop.Service.SecuredService.FileStorageService.IFileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class SecuredProductService implements ISecuredProductService {
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private IFileStorageService fileStorageService;
    @Autowired
    private ICategoryRepository categoryRepository;
    @Override
    public Product addSingleProduct(MultipartFile[] files, Product product) {
        Date dateNow = new Date();
        Product productTemp = new Product();
        String uniqueID = UUID.randomUUID().toString();
        productTemp.setId(uniqueID);
        productTemp.setName(product.getName());
        productTemp.setPrice(product.getPrice());
        productTemp.setDescription(product.getDescription());
        productTemp.setSpecification(product.getSpecification());
        productTemp.setSalePrice(product.getSalePrice());
        ImageDetailProduct imageDetailProduct = new ImageDetailProduct();
        System.out.println(files.length);
        if(files.length != 0) {
            String filepath = fileStorageService.storeFile(files[0], product, uniqueID);
            productTemp.setImageMain(filepath);
            for (int i = 1; i < files.length; i++){
                String filepath1 = fileStorageService.storeFile(files[i], product, uniqueID);
                imageDetailProduct = setImageDetail(imageDetailProduct, i, filepath1);
            }
        }
        productTemp.setImageDetailProduct(imageDetailProduct);
        productTemp.setLastUpdate(dateNow);
        productTemp.setDateCreate(dateNow);
        productTemp.setCategory(product.getCategory());
        imageDetailProduct.setProduct(productTemp);
        System.out.println(imageDetailProduct);
        return productRepository.save(productTemp).orElseThrow(()->new RuntimeException("Cannot save product"));
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Product editProduct(Product product) {
        Date dateNow = new Date();
        Product productTemp = new Product();
        productTemp.setId(product.getId());
        productTemp.setName(product.getName());
        productTemp.setPrice(product.getPrice());
        productTemp.setDescription(product.getDescription());
        productTemp.setSpecification(product.getSpecification());
        productTemp.setSalePrice(product.getSalePrice());
        productTemp.setLastUpdate(dateNow);
        productTemp.setCategory(product.getCategory());
        productTemp.setImageMain(product.getListImage()[0]);
        ImageDetailProduct imageDetailProduct = new ImageDetailProduct();
        for (int i = 1; i < product.getListImage().length; i++){
            imageDetailProduct = setImageDetail(imageDetailProduct, i, product.getListImage()[i]);
        }
        productTemp.setImageDetailProduct(imageDetailProduct);
        System.out.println(imageDetailProduct);;
        imageDetailProduct.setProduct(productTemp);
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

    @Override
    public String saveImage(String id, MultipartFile file, Product product) {
        String filepath = fileStorageService.storeFile(file, product, id);
        return filepath;
    }

    private ImageDetailProduct setImageDetail(ImageDetailProduct imageDetailProduct, int index, String path){
        switch (index){
            case 1 :
                imageDetailProduct.setImageDetails1(path);
                break;
            case 2 :
                imageDetailProduct.setImageDetails2(path);
                break;
            case 3 :
                imageDetailProduct.setImageDetails3(path);
                break;
            case 4 :
                imageDetailProduct.setImageDetails4(path);
                break;
            case 5 :
                imageDetailProduct.setImageDetails5(path);
                break;
            case 6 :
                imageDetailProduct.setImageDetails6(path);
                break;
            default:
                break;
        }
        return imageDetailProduct;
    }
}
