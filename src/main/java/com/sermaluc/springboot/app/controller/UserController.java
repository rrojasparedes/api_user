package com.sermaluc.springboot.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sermaluc.springboot.app.dto.UserResponseDTO;
import com.sermaluc.springboot.app.entity.User;
import com.sermaluc.springboot.app.service.IUserService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("api/users")
public class UserController {

	@Autowired
	private IUserService userService;

	@Operation(summary = "Creación de usuarios")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Usuario creado correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
			@ApiResponse(responseCode = "400", description = "Error con formato de email, contraseña o al leer la solicitud JSON", content = @Content) })
	@PostMapping("/create")
	public ResponseEntity<?> createUser(@RequestBody User user) {

		if (user.getPhones() != null) {
			user.getPhones().forEach(phone -> phone.setUser(user));
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponseDTO(userService.save(user)));
	}

}
