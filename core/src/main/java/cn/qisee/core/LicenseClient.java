package cn.qisee.core;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import feign.Response;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;

@FeignClient(value = "license", path = "/api/auth", configuration = {LicenseClient.MultipartSupportConfig.class})
public interface LicenseClient {

    @GetMapping("/download")
    Response download();

    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    void upload(@RequestPart MultipartFile file);

    @GetMapping("/hello")
    String hello();

    class MultipartSupportConfig{
        @Autowired
        private ObjectFactory<HttpMessageConverters> messageConverters;

        @Bean
        public Encoder feignFormEncoder () {
            return new SpringFormEncoder(new SpringEncoder(messageConverters));
        }
    }
}
