package com.equipo3.inventario.controller;

import com.equipo3.inventario.model.User;
import com.equipo3.inventario.model.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MainController {
  //Página principal
  @GetMapping("/")
  public String getIndexPage(Model model) {
    return "index";
  }

  //Página para sobre nosotros
  @GetMapping("/sobre-nosotros")
  public String getSobreNosotrosPage(Model model) {
    return "sobre-nosotros";
  }

  //Página para acerca de
  @GetMapping("/acerca-de")
  public String getAcercaDePage(Model model) {
    return "acerca-de";
  }
}
