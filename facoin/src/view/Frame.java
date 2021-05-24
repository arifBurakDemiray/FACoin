package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

import settings.AppSettings;

/**
 * This class is the frame of the system
 */
public class Frame extends JFrame {

	private static final long serialVersionUID = -8368745851825149744L;

	/**
	 * It is the constructor of main frame
	 * 
	 * @param name
	 */
	public Frame(String name) {
		super(name);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUpScreenBounds(); // create frame by given bounds
		setBounds(AppSettings.SCREEN_X, AppSettings.SCREEN_Y, AppSettings.WIDTH, AppSettings.HEIGHT);
		setIconImage(AppSettings.ICON.getImage());
	}

	/**
	 * This function sets screen bounds by given user's local screen
	 */
	private void setUpScreenBounds() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // get local screen size
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();

		// set up x and y bounds for local screen in the middle
		AppSettings.SCREEN_X = (int) ((width / 2) - (AppSettings.WIDTH / 2));
		AppSettings.SCREEN_Y = (int) ((height / 2) - (AppSettings.HEIGHT / 2));

	}

}
