package com.ttnhat.shop.Service.PublicService;

import com.ttnhat.shop.DAO.NormalDAO.IProductRepository;
import com.ttnhat.shop.DAO.NormalDAO.IUsersRepository;
import com.ttnhat.shop.Entity.Product;
import com.ttnhat.shop.Sercurity.Entity.UsersEntity;
import com.ttnhat.shop.Tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicService implements IPublicService{
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private IUsersRepository usersRepository;
    @Override
    public Product findProductById(String id) {
        return productRepository.findById(id).orElseThrow(()->new RuntimeException("Cannot find Product"));
    }

    @Override
    public Page<Product> getProductBySearchName(String name, Integer page, Integer size, String category, String types, String sorts) {
        Pageable pageable = PageRequest.of(page, size);
            String typeSort = Tools.convertStringToOrder(types, sorts);
            return productRepository.getProductByName(pageable, name, ConvertCategoryNameToId(category), typeSort);
    }

    @Override
    public UsersEntity signUpUser(UsersEntity usersEntity) {
        Date date = new Date();
        usersEntity.setDateCreated(date);
        usersEntity.setRoles("CUSTOMER");
        usersEntity.setStatus(1);
        return usersRepository.signUp(usersEntity);
    }

    private Integer ConvertCategoryNameToId(String category){
        switch (category){
            case "Phones":
                return 1;
            case "Laptops":
                return 2;
            case "Watches":
                return 3;
            case "Accessories":
                return 4;
            case "Cameras":
                return 5;
            case "Sports":
                return 6;
            default:
                return 0;
        }
    }
}
