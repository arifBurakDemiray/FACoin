package view.color;

import java.awt.Color;

/**
 * light theme for the palette
 */
public class LightTheme extends Theme {

	public LightTheme() {
		super();
		LIGHT_THEME();
	}

	/**
	 * Sets the colors for the light theme
	 */
	private void LIGHT_THEME() {
		this.background = new Color(245, 248, 250); // White color, #F5F8FA https://encycolorpedia.com/f5f8fa
		this.firstColor = new Color(240, 185, 11); // Gold color, #F0B90B https://encycolorpedia.com/f0b90b
		this.secondColor = new Color(29, 161, 242); // Blue color, #1DA1F2 https://encycolorpedia.com/1da1f2
	}
}
