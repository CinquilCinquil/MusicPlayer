package service;

import entity.User;
import repository.UserRepository;
import util.LoginState;

public class LoginService {

	private UserRepository userRepository;
	
	public LoginService() {
		userRepository = new UserRepository();
	}
	
	public LoginState logIn(String name, String password) {
		int id = getUserId(name);
		
		if (id >= 1)
		{	
			// if the password is valid
			if (userRepository.getUserPassword(id).compareTo(password) == 0)
			{
				return LoginState.OK;
			}
			else {
				return LoginState.PASSWORD;
			}
		}
		else {
			return LoginState.USER;
		}
	}
	
	public int getUserId(String name) {
		return userRepository.getUserId(name);
	}
	
	public boolean isVip(String name) {
		return userRepository.isVip(getUserId(name));
	}
	
	public boolean addUser(String name, String password, boolean vip) {
		if (userRepository.getUserId(name) == -1) { // if there are no users with the same name
			
			userRepository.create(new User(name));
			userRepository.setPassword(userRepository.getUserId(name), password);
			userRepository.setVip(userRepository.getUserId(name), vip);
			
			return true;
		}
		else {
			return false;
		}
	}
	
}
