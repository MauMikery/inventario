package com.equipo3.inventario.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

//Esta anotacion muestra que hibernate debe crear una tabla en esta clase
//Hibernate es una herramienta de mapeo objeto-relacional(ORM)
@Entity
@Data
public class User {
  //Los atributos se volverán en campos de la db

  //Define un campo Id y que la clave se generé en automático
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  //Valida que no esté vacío los campos
  @NotEmpty
  private String username;
  @NotEmpty
  private String email;
  @NotEmpty
  private String phone;
  @NotEmpty
  private String password;
}
