package com.equipo3.inventario.model;

import java.util.List;

public interface ProductService {
  //Método para leer los productos (Read)
  public List<Product> readProducts();

  //Método para crear y modificar products (Create y Update)
  public void saveProduct(Product product);

  //Método para borrar productos (Delete)
  public void dropProduct(Product product);

  //Método para localizar productos
  public Product findProduct(Product product);
}
