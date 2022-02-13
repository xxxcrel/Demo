package com.cheeseocean.core;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import feign.Response;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@RestController
@RequestMapping("/api")
public class CoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class);
    }
    @Autowired
    private LicenseClient licenseClient;

    @GetMapping("/download")
    public void download(HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=lic_ext_key.json");
        Response resp = licenseClient.download();
        System.out.println(resp.reason());
        System.out.println(resp.status());
        StreamUtils.copy(resp.body().asInputStream(), response.getOutputStream());
    }

    @GetMapping("/hello")
    public String hello(){
        return licenseClient.hello();
    }

    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void upload(MultipartHttpServletRequest request){
        Map<String, MultipartFile> fileMap = request.getFileMap();
        fileMap.forEach((s, file) -> {
            System.out.println("upload file name" + s);
            licenseClient.upload(file);
        });
    }
}
