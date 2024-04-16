package com.equipo3.inventario.controller;

import com.equipo3.inventario.model.Product;
import com.equipo3.inventario.model.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/productos")
public class ProductController {
  @Autowired
  private ProductService productService;

  //Página para gestionar productos
  @GetMapping("")
  public String getProductosPage(Model model) {
    ////Obtenemos los usuarios de la base de datos
    List<Product> products = productService.readProducts();
    //Los agregamos al objeto model para mostrarlos en la interfaz
    model.addAttribute("products", products);

    return "productos";
  }

  //Página para añadir un producto
  @GetMapping("/agregar")
  public String getAgregarPage(Model model) {
    model.addAttribute("product", new Product());

    return "agregarProducto";
  }

  //Ruta para procesar los datos del producto añadido
  @PostMapping("/agregar")
  public String postAgregarPage(Product product){
    //Añadimos el producto o sus modificaciones
    productService.saveProduct(product);

    return "redirect:/productos";
  }

  //Ruta para modificar un producto
  @GetMapping("/cambiar/{id}")
  public String getCambiarPage(Product product, Model model){
    product = productService.findProduct(product);
    model.addAttribute("product", product);

    return "agregarProducto";
  }

  //Ruta para eliminar un producto
  @GetMapping("/eliminar/{id}")
  public String getEliminarPage(Product product){
    productService.dropProduct(product);

    return "redirect:/productos";
  }

}
