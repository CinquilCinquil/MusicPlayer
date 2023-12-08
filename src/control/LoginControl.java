package control;

import service.LoginService;
import util.LoginState;

public class LoginControl {
	
	private LoginService service;
	
	public LoginControl() {
		service = new LoginService();
	}
	
	public LoginState logIn(String name, String password) {
		return service.logIn(name, password);
	}
	
	public int getUserId(String name) {
		return service.getUserId(name);
	}
	
	public boolean isVip(String name) {
		return service.isVip(name);
	}
	
	public boolean addUser(String name, String password, boolean vip) {
		return service.addUser(name, password, vip);
	}

}
