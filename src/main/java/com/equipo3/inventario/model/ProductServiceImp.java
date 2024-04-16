package com.equipo3.inventario.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {
  @Autowired
  private ProductRepository productRepository;

  @Override
  @Transactional(readOnly = true)
  public List<Product> readProducts() {
    return (List<Product>) productRepository.findAll();
  }

  @Override
  @Transactional
  public void saveProduct(Product product) {
    productRepository.save(product);
  }

  @Override
  @Transactional
  public void dropProduct(Product product) {
    productRepository.delete(product);
  }

  @Override
  @Transactional(readOnly = true)
  public Product findProduct(Product product) {
    return productRepository.findById(product.getId()).orElse(null);
  }
}
