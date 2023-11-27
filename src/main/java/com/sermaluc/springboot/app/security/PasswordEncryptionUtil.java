package com.sermaluc.springboot.app.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordEncryptionUtil {

	public static String encryptPassword(String password) {
		if (password != null && !password.isEmpty()) {
			try {
				MessageDigest md = MessageDigest.getInstance("SHA-256");
				byte[] hashedPassword = md.digest(password.getBytes());
				return Base64.getEncoder().encodeToString(hashedPassword);
			} catch (NoSuchAlgorithmException e) {
				throw new RuntimeException("Error al encriptar el password", e);
			}
		}
		return null;
	}
}
