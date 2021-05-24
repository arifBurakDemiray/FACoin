package view.decorator;

import view.IColorable;
import view.color.ColorPalette;
import view.color.DarkTheme;

/**
 * This class paints a view by dark theme
 */
public class DarkThemeDecorator extends ThemeDecorator {

	public DarkThemeDecorator(IColorable view) {
		super(view);
	}

	@Override
	public void set() {
		ColorPalette palette = new ColorPalette(new DarkTheme()); // paint colorable dark
		this.view.setPalette(palette);
	}

}
