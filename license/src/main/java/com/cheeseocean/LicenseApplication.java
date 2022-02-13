package com.cheeseocean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@RestController
@RequestMapping("/api/auth")
public class LicenseApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(LicenseApplication.class)
                .run(args);
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void upload(@RequestPart MultipartFile file) throws IOException {
        System.out.println("Received file: " + file.getName());
        File localFile = new File(System.getProperty("user.home") + "/tmp.json");
        if(localFile.exists()){
            StreamUtils.copy(file.getInputStream(), new FileOutputStream(localFile));
        }
    }

    @GetMapping("/download")
    public void download(HttpServletResponse response) throws IOException {
        File file = new File(System.getProperty("user.home") + "/lic_ext_key.json");
        System.out.println("License Service starting download");
        if (file.exists()) {
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lic_ext_key.json");
            StreamUtils.copy(new FileInputStream(file), response.getOutputStream());
        }
    }
}
