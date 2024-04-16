package com.equipo3.inventario;

import com.equipo3.inventario.model.Product;
import com.equipo3.inventario.model.ProductRepository;
import com.equipo3.inventario.model.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class InventarioApplicationTestsProduct {
	@Autowired
	private ProductService productService;

	@MockBean
	private ProductRepository productRepository;

	//Test para verificar creacion y modificacion de productos
	@Test
	public void testCreateProduct(){
		Product product = new Product();
		product.setId(3L);
		product.setName("Sabritas");
		product.setQuantity(80);
		product.setPrice(20.50);

		// Configuración del mock para el método saveProduct()
		when(productRepository.save(product)).thenReturn(product);

		// Ejecutar el método a probar
		productService.saveProduct(product);

		// Verificar que se haya llamado al método saveProduct() del repositorio
		verify(productRepository, times(1)).save(product);
		verifyNoMoreInteractions(productRepository);
	}

	//Test para verificar lectura de productos
	@Test
	public void testReadProducts() {
		Product product1 = new Product();
		product1.setId(1L);
		product1.setName("Producto 1");
		product1.setQuantity(55);
		product1.setPrice(55.40);

		Product product2 = new Product();
		product2.setId(2L);
		product2.setName("Producto 2");
		product2.setQuantity(58);
		product2.setPrice(58.40);

		List<Product> productList = Arrays.asList(product1, product2);

		// Configuración del repositorio mock
		when(productRepository.findAll()).thenReturn(productList);

		// Ejecución del método a probar
		List<Product> result = productService.readProducts();

		// Verificación de resultados
		assertEquals(2, result.size());
		assertEquals("Producto 1", result.get(0).getName());
		assertEquals("Producto 2", result.get(1).getName());
		assertEquals(55, result.get(0).getQuantity());
		assertEquals(55.40, result.get(0).getPrice(), 0.01); // Aquí 0.01 es el margen de error permitido para comparar números de punto flotante
		assertEquals(58, result.get(1).getQuantity());
		assertEquals(58.40, result.get(1).getPrice(), 0.01);
	}

	//Test para verificar la eliminacion de un producto
	@Test
	public void testDropProduct() {
		// Creamos un producto para eliminar
		Product productToDelete = new Product();
		productToDelete.setId(3L);
		productToDelete.setName("Sabritas");
		productToDelete.setQuantity(80);
		productToDelete.setPrice(20.50);

		// Configuramos el mock para el método delete()
		doNothing().when(productRepository).delete(productToDelete);

		// Ejecutar el método a probar
		productService.dropProduct(productToDelete);

		// Verificar que se haya llamado al método delete() del repositorio
		verify(productRepository, times(1)).delete(productToDelete);
		verifyNoMoreInteractions(productRepository);
	}

}
