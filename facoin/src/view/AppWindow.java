package view;

import javax.swing.JWindow;

import controller.IConsumable;

/**
 * This class is the window of the system
 */
public class AppWindow extends JWindow implements IConsumable {

	private static final long serialVersionUID = -8130256364738099887L;

	// Frame of the system
	protected static Frame FRAME;

	/**
	 * Creation of the system frame
	 */
	public AppWindow() {
		FRAME = new Frame("FACoin");
		FRAME.setVisible(true);
	}

	@Override
	public void supressNotUsed() {
	}
}
