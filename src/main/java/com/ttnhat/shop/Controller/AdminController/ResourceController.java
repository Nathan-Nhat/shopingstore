package com.ttnhat.shop.Controller.AdminController;

import com.ttnhat.shop.Service.AdminService.FileStorageService.FileStorageProperties;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class ResourceController {

    @Autowired
    private FileStorageProperties fileStorageProperties;
    @RequestMapping(value = "/image/{categoryId}/{productId}/{name}", method = RequestMethod.GET)
    public void getImage(@PathVariable(name = "name") String name, @PathVariable(name = "categoryId") String categoryId,
                         @PathVariable(name = "productId") String productId,  HttpServletResponse response){
        String filePath = fileStorageProperties.getLocation() + categoryId + "/" + productId + "/" + name;
        try {
            File file = ResourceUtils.getFile(filePath);
            InputStream in = new FileInputStream(file);
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            IOUtils.copy(in, response.getOutputStream());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
