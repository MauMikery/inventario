package com.equipo3.inventario.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
  @Autowired
  private UserRepository userRepository;

  @Override
  @Transactional(readOnly = true)
  public List<User> readUsers() {
    return (List<User>) userRepository.findAll();
  }

  @Override
  @Transactional
  public void saveUser(User user) {
    //Encriptamos la contraseña antes de agregar el objeto
    BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
    user.setPassword(crypt.encode(user.getPassword()));

    userRepository.save(user);
  }

  @Override
  @Transactional
  public void dropUser(User user) {
    userRepository.delete(user);
  }

  @Override
  @Transactional(readOnly = true)
  public User findUser(User user) {
    return userRepository.findById(user.getId()).orElse(null);
  }
}
