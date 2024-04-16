package com.equipo3.inventario.model;

import java.util.List;

public interface UserService {
  //Método para leer los usuarios (Read)
  public List<User> readUsers();

  //Método para crear y modificar usuarios (Create y Update)
  public void saveUser(User user);

  //Método para borrar usuarios (Delete)
  public void dropUser(User user);

  //Método para localizar usuarios
  public User findUser(User user);

}
