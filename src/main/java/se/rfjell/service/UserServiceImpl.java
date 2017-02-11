package se.rfjell.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import se.rfjell.model.StudentTest;
import se.rfjell.model.User;
import se.rfjell.repository.UserRepository;
import se.rfjell.util.Mail;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private Mail mail;
	
	@Override
	public User save(User user) {
		if( userRepository.findByUsername(user.getUsername()) != null)
			return null;
		changePassword(user, user.getPassword());

		user.setRole("USER");
		user.setEnabled('Y');

		//Email stuff
/*		user.setEnabled('N');

		//Send validation email
		user.setConfirmationLink();
		mail.sendValidationMail(user.getEmail(), user.getConfirmationLink());
*/
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
	public User update(User user, User newUserInfo, String currentPassword) {
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		
		if( !bcpe.matches(currentPassword, user.getPassword()) )
			return null;

		if( newUserInfo.getPassword() != null && newUserInfo.getPassword().trim().length() > 0  ) {
			changePassword(user, newUserInfo.getPassword());
		}
		if( newUserInfo.getAddress() != null && newUserInfo.getAddress().trim().length() > 0){
			user.setAddress(newUserInfo.getAddress());
		}
		if( newUserInfo.getFirstName() != null && newUserInfo.getFirstName().trim().length() > 0){
			user.setFirstName(newUserInfo.getFirstName());
		}
		if( newUserInfo.getLastName() != null && newUserInfo.getLastName().trim().length() > 0){
			user.setLastName(newUserInfo.getLastName());
		}
		if( newUserInfo.getZipCode() != null && newUserInfo.getZipCode().trim().length() > 0){
			user.setZipCode(newUserInfo.getZipCode());
		}
		if( newUserInfo.getUsername() != null && newUserInfo.getUsername().trim().length() > 0){
			user.setUsername(newUserInfo.getUsername());
			//send new confirmation email
		}
		if( newUserInfo.getGender() != null && newUserInfo.getGender().trim().length() > 0){
			user.setGender(newUserInfo.getGender());
		}
		if( newUserInfo.getTelephone() != null && newUserInfo.getTelephone().trim().length() > 0){
			user.setTelephone(newUserInfo.getTelephone());
		}
		return userRepository.save(user);
	}

	@Override
	public User update(User user) {
		return userRepository.save(user);
	}

	@Override
	public User update(User user, StudentTest st) {
		user.addStudentTest(st);
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
	
	@Override
	public User findByConfirmationLink(String id){
		User user = userRepository.findByConfirmationLink(id);
		if(user != null) {
			user.setEnabled('Y');
			user.setConfirmationLink("");
			return userRepository.save(user);
		}

		return null;
	}

	public User getLoggedInUser(HttpSession session) {
		SecurityContextImpl sci = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
		String username = sci.getAuthentication().getName();

		User user = findByUsername(username);
		return user;
	}

	private void changePassword(User user, String password) {
		//Hash password
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		user.setPassword( bcpe.encode(password));
    }
}
