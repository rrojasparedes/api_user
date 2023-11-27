package com.sermaluc.springboot.app.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.sermaluc.springboot.app.security.PasswordEncryptionUtil;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String password;
	private Date created;
	private Date modified;
	private boolean isactive;
	private String token;
	private Date lastLogin;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Phone> phones;

	@PrePersist
	public void prePersist() {
		created = new Date();
		modified = new Date();
		lastLogin = new Date();
		token = UUID.randomUUID().toString();

		encryptPasswordIfNotEmpty();
	}

	@PreUpdate
	public void preUpdate() {
		modified = new Date();
		encryptPasswordIfNotEmpty();
	}

	private void encryptPasswordIfNotEmpty() {
		if (password != null && !password.isEmpty()) {
			this.password = PasswordEncryptionUtil.encryptPassword(password);
		}
	}

}
