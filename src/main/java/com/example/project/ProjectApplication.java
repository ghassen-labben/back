package com.example.project;

import com.example.project.models.Product;
import com.example.project.repositories.ProductRepository;
import com.example.project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class ProjectApplication  implements CommandLineRunner {
	@Autowired
	private ProductRepository productRepository;
	public static void main(String[] args) {

		SpringApplication.run(ProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Product product=new Product("SAMSUNG Galaxy A15 5G A Series Cell Phone, 128GB Unlocked Android Smartphone,","https://m.media-amazon.com/images/I/61s0ZzwzSCL._AC_UY327_FMwebp_QL65_.jpg",199);
		Product product1=new Product("Google Pixel 7 Pro - 5G Android Phone","https://m.media-amazon.com/images/I/61bFypVJVyL._AC_UY327_FMwebp_QL65_.jpg",375);
		Product product2=new Product("SAMSUNG Galaxy A25 5G A,","https://m.media-amazon.com/images/I/61cwMOVn17L._AC_UY327_FMwebp_QL65_.jpg",232.334);
		Product product3=new Product("SAMSUNG Galaxy S24+ Plus","https://m.media-amazon.com/images/I/71yc5A1EavL._AC_UY327_FMwebp_QL65_.jpg",886);
		Product product4=new Product("Motorola Moto G Stylus 5G","https://m.media-amazon.com/images/I/51bfhhUKhTL._AC_UY327_FMwebp_QL65_.jpg",478);

		productRepository.saveAll(Arrays.asList(product,product1,product2,product3,product4));
	}
}
