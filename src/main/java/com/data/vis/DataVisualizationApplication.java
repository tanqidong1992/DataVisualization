package com.data.vis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= {"com"})
public class DataVisualizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataVisualizationApplication.class, args);
	}
}
