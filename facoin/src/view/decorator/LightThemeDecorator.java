package view.decorator;

import view.IColorable;
import view.color.ColorPalette;
import view.color.LightTheme;

/**
 * This class paints a view by light theme
 */
public class LightThemeDecorator extends ThemeDecorator {

	public LightThemeDecorator(IColorable view) {
		super(view);
	}

	@Override
	public void set() {
		ColorPalette palette = new ColorPalette(new LightTheme()); // paint colorable light
		this.view.setPalette(palette);
	}

}
