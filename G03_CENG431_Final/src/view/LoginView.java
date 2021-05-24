package view;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import settings.AppSettings;
import view.color.ColorPalette;
import view.list.List;

/**
 * The login view is a view which user can log in the system.
 *
 */
public class LoginView extends AppView {

	private static final long serialVersionUID = 7766903621224003360L;
	private JButton loginButton; // login button
	private JPasswordField password; // password field
	private JTextField userName; // user name field
	private JLabel message; // warning message according to the situation
	private ColorPalette palette;
	private JLabel logo;

	public LoginView() {

		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);

		loginButton = new JButton("Sign in");
		loginButton.setBounds(300, 320, 200, 40);
		loginButton.setFont(new Font("Arial", Font.BOLD, 20));
		add(loginButton);

		password = new JPasswordField("Password");
		password.setBounds(300, 260, 200, 40);
		password.setFont(new Font("Arial", Font.PLAIN, 20));
		add(password);

		userName = new JTextField("Username");
		userName.setBounds(300, 200, 200, 40);
		userName.setFont(new Font("Arial", Font.PLAIN, 20));
		add(userName);

		logo = new JLabel(AppSettings.LOGO);
		logo.setBounds(AppSettings.WIDTH - AppSettings.SCREEN_X, 0, 200, 200);
		add(logo);

		message = new JLabel("Incorrect login, please try again");
		message.setBounds(265, 400, 300, 30);
		message.setFont(new Font("Arial", Font.PLAIN, 20));
		add(message);
		message.setVisible(false);
		AppWindow.FRAME.getContentPane().removeAll(); // remove content of the system frame
		AppWindow.FRAME.getContentPane().add(this); // add this to frame pane
		AppWindow.FRAME.setVisible(true); // and set this ones visibility

	}

	/**
	 * The function helps for detecting of clicking login button using listener
	 * 
	 * @param listener
	 */
	public void addLoginListener(ActionListener loginListener) {
		loginButton.addActionListener(loginListener);
	}

	/**
	 * The function helps for detecting of change in the user name field using
	 * listener
	 * 
	 * @param listener
	 */
	public void addTextListener(MouseListener textListener) {
		userName.addMouseListener(textListener);
	}

	/**
	 * The function returns the filled password
	 * 
	 * @return String = password text
	 */
	public char[] getPassword() {
		return password.getPassword();
	}

	/**
	 * The function returns the filled user name
	 * 
	 * @return String = user name text
	 */
	public JTextField getUserName() {
		return userName;
	}

	/**
	 * If user is not authenticated, show the warning message
	 * 
	 * @param isAuthenticated
	 */
	public void printMessage(boolean isAuthenticated) {
		if (isAuthenticated)
			setVisible(false);
		else
			message.setVisible(true);
	}

	@Override
	public void setPalette(ColorPalette palette) {
		this.palette = palette;
		updateColors();
	}

	/**
	 * This functiýn updates color of entities by given palette
	 */
	private void updateColors() {
		setBackground(palette.BACKGROUND);
		loginButton.setBackground(palette.FIRST_COLOR);
		loginButton.setForeground(palette.BACKGROUND);
		message.setForeground(palette.SECOND_COLOR);
		userName.setBackground(palette.BACKGROUND);
		userName.setForeground(palette.FIRST_COLOR);
		password.setBackground(palette.BACKGROUND);
		password.setForeground(palette.FIRST_COLOR);
	}

	@Override
	public void setList(List list) {
		// This view not supports set list function
	}

	@Override
	public List getList() {
		// This view not supports get list function
		return null;
	}

}