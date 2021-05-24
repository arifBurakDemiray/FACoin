package view.color;

import java.awt.Color;

/**
 * Default theme for the system
 */
public class DefaultTheme extends Theme {

	protected DefaultTheme() {
		super();
		DEFAULT_THEME();
	}

	/**
	 * Sets the theme colors for default theme
	 */
	private void DEFAULT_THEME() {
		this.background = Color.WHITE;
		this.firstColor = Color.BLACK;
		this.secondColor = Color.BLACK;
	}
}
