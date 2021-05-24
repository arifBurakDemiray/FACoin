package view.color;

import java.awt.Color;

/**
 * This class is the palette for the given themes
 */
public class ColorPalette {

	public final Color SECOND_COLOR;
	public final Color BACKGROUND;
	public final Color FIRST_COLOR;

	/**
	 * A palette can be modified by give ntheme
	 * 
	 * @param theme that changes colors of the palette
	 */
	public ColorPalette(Theme theme) {
		this.SECOND_COLOR = theme.secondColor;
		this.FIRST_COLOR = theme.firstColor;
		this.BACKGROUND = theme.background;
	}
}
