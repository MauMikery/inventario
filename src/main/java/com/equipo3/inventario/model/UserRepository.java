package com.equipo3.inventario.model;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
  //Esta interfaz implementa metodos para
  // CREATE, READ, UPDATE Y DELETE en la DB y la tabla User
}
