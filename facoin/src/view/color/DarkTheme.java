package view.color;

import java.awt.Color;

/**
 * Dark theme for the palette
 */
public class DarkTheme extends Theme {

	public DarkTheme() {
		super();
		DARK_THEME();
	}

	/**
	 * Sets theme's colors for dark theme
	 */
	private void DARK_THEME() {
		this.background = new Color(20, 23, 26); // Black color, #14171A https://encycolorpedia.com/14171a
		this.firstColor = new Color(240, 185, 11); // Gold color, #F0B90B https://encycolorpedia.com/f0b90b
		this.secondColor = new Color(29, 161, 242); // Blue color, #1DA1F2 https://encycolorpedia.com/1da1f2
	}
}