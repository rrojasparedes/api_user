package com.sermaluc.springboot.app.dto;

import lombok.Data;

@Data
public class CustomErrorResponseDTO {

	private String mensaje;

	public CustomErrorResponseDTO(String mensaje) {
		this.mensaje = mensaje;
	}
}