package com.sermaluc.springboot.app.dto;

import lombok.Data;
import java.util.Date;

import com.sermaluc.springboot.app.entity.User;

@Data
public class UserResponseDTO {

	private Long id;
	private Date created;
	private Date modified;
	private Date lastLogin;
	private String token;
	private boolean isActive;

	public UserResponseDTO(User user) {
		if (user != null) {
			this.id = user.getId();
			this.created = user.getCreated();
			this.modified = user.getModified();
			this.lastLogin = user.getLastLogin();
			this.token = user.getToken();
			this.isActive = user.isIsactive();
		}
	}
}
