package pe.edu.upao.petcare.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootApplication
public class WebApplication {
	public static void main(String[] args) throws IOException {
		SpringApplication.run(WebApplication.class, args);

	}

}
