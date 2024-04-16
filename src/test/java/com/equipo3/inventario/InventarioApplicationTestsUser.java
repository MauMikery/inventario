package com.equipo3.inventario;

import com.equipo3.inventario.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
class InventarioApplicationTestsUser {
	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImp userService;

	private BCryptPasswordEncoder passwordEncoder;


	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		passwordEncoder = new BCryptPasswordEncoder();

	}

	@Test
	public void testReadUsers() {
		// Crear una lista de usuarios simulada
		List<User> userList = new ArrayList<>();
		User user1 = new User();
		User user2 = new User();

		user1.setId(1L);
		user2.setId(2L);

		user1.setUsername("user1");
		user2.setUsername("user2");

		user1.setEmail("user1@gmail.com");
		user2.setEmail("user2@gmail.com");

		user1.setPhone("123456789");
		user2.setPhone("987654321");

		user1.setPassword("password1");
		user2.setPassword("password2");

		userList.add(user1);
		userList.add(user2);

		// Configurar el comportamiento del repositorio mock
		when(userRepository.findAll()).thenReturn(userList);

		// Ejecutar el método a probar
		List<User> result = userService.readUsers();

		// Verificar que el resultado sea el esperado
		assertEquals(userList.size(), result.size());
		assertEquals(userList.get(0).getUsername(), result.get(0).getUsername());
		assertEquals(userList.get(1).getUsername(), result.get(1).getUsername());

		// Verificar que se haya llamado al método findAll() del repositorio
		verify(userRepository, times(1)).findAll();
	}

	@Test
	public void testSaveUser() {
		// Crear un usuario simulado
		User user = new User();
		user.setId(3L);
		user.setUsername("user3");
		user.setEmail("user3@gmail.com");
		user.setPhone("111222333");
		user.setPassword("password3");

		// Ejecutar el método a probar
		userService.saveUser(user);

		// Obtener la contraseña encriptada del usuario desde la base de datos (simulacion)
		when(userRepository.findById(3L)).thenReturn(java.util.Optional.of(user));

		// Verificar que la contraseña encriptada coincida con la contraseña original
		assertTrue(passwordEncoder.matches("password3", user.getPassword()));
	}

	@Test
	public void testDropUser() {
		// Crear un usuario simulado
		User user = new User();
		user.setId(4L);
		user.setUsername("user4");
		user.setEmail("user4@gmail.com");
		user.setPhone("4445556666");
		user.setPassword("password4");

		// Configurar el comportamiento del repositorio mock
		doNothing().when(userRepository).delete(user);

		// Ejecutar el método a probar
		userService.dropUser(user);

		// Verificar que se haya llamado al método delete() del repositorio
		verify(userRepository, times(1)).delete(user);
	}
}
