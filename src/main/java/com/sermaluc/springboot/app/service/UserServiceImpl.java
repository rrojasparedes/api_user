package com.sermaluc.springboot.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sermaluc.springboot.app.entity.User;
import com.sermaluc.springboot.app.exception.EmailAlreadyRegisteredException;
import com.sermaluc.springboot.app.exception.InvalidEmailException;
import com.sermaluc.springboot.app.exception.InvalidPasswordException;
import com.sermaluc.springboot.app.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Value("${password.regex}")
	private String passwordRegex;

	@Value("${email.regex}")
	private String emailRegex;

	@Value("${email.invalid}")
	private String emailInvalid;

	@Value("${password.invalid}")
	private String passwordInvalid;

	@Value("${email.registered}")
	private String emailRegistered;

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public User save(User user) {

		User existingUser = userRepository.findByEmail(user.getEmail());

		if (!user.getPassword().matches(passwordRegex)) {
			throw new InvalidPasswordException(passwordInvalid);
		} else if (!user.getEmail().matches(emailRegex)) {
			throw new InvalidEmailException(emailInvalid);
		} else if (existingUser != null) {
			throw new EmailAlreadyRegisteredException(emailRegistered);
		}

		return userRepository.save(user);
	}

}
