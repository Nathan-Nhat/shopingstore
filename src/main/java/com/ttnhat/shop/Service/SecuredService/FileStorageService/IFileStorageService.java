package com.ttnhat.shop.Service.SecuredService.FileStorageService;

import com.ttnhat.shop.Entity.Product;
import org.springframework.web.multipart.MultipartFile;

public interface IFileStorageService {
    String storeFile(MultipartFile multipartFile, Product product, String uuid);
}
