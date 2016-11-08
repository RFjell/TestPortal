package se.rfjell.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import se.rfjell.model.User;
import se.rfjell.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User save(User user) {
		changePassword(user, user.getPassword());

		user.setRole("USER");

		//Send mail
		//sendMail(user);

		return userRepository.save(user);
	}

	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User update(User user, User newUserInfo, String newPassword) {
		if( newPassword != null && newPassword.trim().length() > 0  ) {
			changePassword(user, newPassword);
		}

		return userRepository.save(user);
	}

	@Override
	public User update(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> findByRole(String role) {
		return userRepository.findByRole(role);
	}

	@Override
	public User findById(Long id) {
		return userRepository.findOne(id);
	}

	private void changePassword(User user, String password) {
		//Hash password
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		user.setPassword( bcpe.encode(password));
    }
}
