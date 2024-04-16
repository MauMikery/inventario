package com.equipo3.inventario.controller;

import com.equipo3.inventario.model.User;
import com.equipo3.inventario.model.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UserController {
  @Autowired
  private UserService userService;

  //Página para gestionar usuarios
  @GetMapping("")
  public String getUsuariosPage(Model model) {
    //Obtenemos los usuarios de la base de datos
    List<User> users = userService.readUsers();
    //Los agregamos al objeto model para mostrarlos en la interfaz
    model.addAttribute("users", users);
    return "usuarios";
  }

  //Página para añadir un usuario
  @GetMapping("/agregar")
  public String getAgregarPage(Model model) {
    model.addAttribute("user", new User());

    return "agregarUsuario";
  }

  //Ruta para procesar los datos del usuario añadido
  @PostMapping("/agregar")
  public String postAgregarPage(User user){
    //Añadimos el usuario o sus modificaciones
    userService.saveUser(user);

    return "redirect:/usuarios";
  }

  //Ruta para modificar un usuario
  @GetMapping("/cambiar/{id}")
  public String getCambiarPage(User user, Model model){
    user = userService.findUser(user);
    model.addAttribute("user", user);

    return "cambiarUsuario";
  }

  //Ruta para eliminar un usuario
  @GetMapping("/eliminar/{id}")
  public String getEliminarPage(User user){
    userService.dropUser(user);

    return "redirect:/usuarios";
  }
}
