package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTextField;

import mediator.LoginMediator;

/**
 * This class handles login screen actions
 */
public class LoginController {

	private LoginMediator mediator;

	public LoginController(LoginMediator mediator) {
		this.mediator = mediator;
		this.mediator.getView().addLoginListener(new LoginListener()); // add listeners
		this.mediator.getView().addTextListener(new TextClickListener());
	}

	// This class listens login button, if pressed user is going to be checked for
	// login
	class LoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mediator.login();
		}
	}

	// This class listens text areas, if user clicks text, removes the text
	class TextClickListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			Object field = e.getSource();
			if (field instanceof JTextField) {
				JTextField temp = (JTextField) field;
				if (temp.getText().equals("Username")) { // if text is initial, remove it
					temp.setText("");
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

	}
}
