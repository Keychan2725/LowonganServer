package com.java.lowongan.lowongan_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


@SpringBootApplication
public class LowonganServerApplication {


	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(50 * 1024 * 1024); // Set maximum upload size to 50MB
		return multipartResolver;
	}

	public static void main(String[] args) {
		SpringApplication.run(LowonganServerApplication.class, args);


	}


}
