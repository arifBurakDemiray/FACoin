package mediator;

import view.LoginView;
import view.decorator.DarkThemeDecorator;
import controller.Consumable;
import controller.LoginController;
import model.User;
import service.AuthService;

public class LoginMediator extends Consumable {

	private AuthService service;
	private LoginView view;
	private LoginController controller;

	public LoginMediator() {
		service = new AuthService();
		view = new LoginView();
		new DarkThemeDecorator(view);
		controller = new LoginController(this);
	}

	/**
	 * The functions gets the login inputs from view and tries to authenticate user
	 * using auth service If user is authorised, display home page
	 */
	public void login() {
		User user = service.login(view.getUserName().getText(), view.getPassword());
		if (user == null) {
			view.printMessage(false);
		} else { // if true means not print the message
			view.printMessage(controller instanceof LoginController);
			HomeMediator homeMediator = new HomeMediator(user);
			view.setVisible(!(homeMediator instanceof HomeMediator));

		}
	}

	public LoginView getView() {
		return view;
	}
}
