package com.equipo3.inventario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.equipo3.inventario")
public class InventarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventarioApplication.class, args);
	}

}
