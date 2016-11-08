package se.rfjell.config;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import se.rfjell.repository.UserRepository;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		se.rfjell.model.User user = userRepository.findByUsername(username);
		if( user == null ) {
			throw new UsernameNotFoundException("Username " + username + " not found");
		}
		return new org.springframework.security.core.userdetails.User(username, user.getPassword(), getGrantedAuthorities(user));
	}

	private Collection<? extends GrantedAuthority> getGrantedAuthorities(se.rfjell.model.User user) {
		Collection<? extends GrantedAuthority> authorities;

		if( user.getRole().equals("ROOT")) {
			authorities = Arrays.asList( () -> "ROLE_ROOT", () -> "ROLE_ADMIN", () -> "ROLE_USER");
		} else if( user.getRole().equals("ADMIN")) {
			authorities = Arrays.asList( () -> "ROLE_ADMIN", () -> "ROLE_USER");
		} else {
			authorities = Arrays.asList( () -> "ROLE_USER");
		}
		return authorities;
	}
}