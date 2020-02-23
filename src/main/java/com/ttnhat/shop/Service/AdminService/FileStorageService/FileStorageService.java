package com.ttnhat.shop.Service.AdminService.FileStorageService;

import com.ttnhat.shop.Entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService implements IFileStorageService{
    private Path rootPath;
    private final static String baseUrl = "http://localhost:8080/image/";
    private  FileStorageProperties fileStorageProperties;
    @Autowired
    public FileStorageService(FileStorageProperties storage) {
        this.rootPath = Paths.get(storage.getLocation());
        this.fileStorageProperties = storage;
    }

    @PostConstruct
    public void initStorage(){
        try {
            if (!Files.isDirectory(this.rootPath))
            {
                Files.createDirectories(this.rootPath);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    @Override
    public String storeFile(MultipartFile multipartFile, Product product, String name) {
        String idPathStr = fileStorageProperties.getLocation() + "/" + product.getCategory().getId();
        Path idPath = Paths.get(idPathStr);
        String filename = null;
        try {
            if (!Files.isDirectory(idPath))
            {
                Files.createDirectories(idPath);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        String productPathStr = idPathStr  + "/"+ product.getId();
        Path idProductPath = Paths.get(productPathStr);
        try {
            if (!Files.isDirectory(idProductPath))
            {
                Files.createDirectories(idProductPath);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        filename = name + "." + multipartFile.getOriginalFilename()
                    .substring(
                            multipartFile.getOriginalFilename().lastIndexOf(".") + 1);
        String filePath = this.baseUrl + product.getCategory().getId().toString() + "/" + product.getId() + "/" + filename;
        try {
            InputStream inputStream = multipartFile.getInputStream();
            Files.copy(inputStream, idProductPath.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
            return filePath;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private String removeSpace(String input){
        String ret = input.replaceAll("\\s+", "");
        return ret;
    }
}
