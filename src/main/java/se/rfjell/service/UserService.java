package se.rfjell.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import se.rfjell.model.StudentTest;
import se.rfjell.model.User;

public interface UserService {
	User save(User user);
	
	List<User> findAllUsers();
	
	User findByUsername(String username);
	
	User update(User user, User newUserInfo, String newPassword);

	User update(User user, StudentTest st);

	User update(User user);

	List<User> findByRole(String role);

	User findById(Long id);

	User findByConfirmationLink(String id);

	User getLoggedInUser(HttpSession session);

}
