package view;

import view.color.ColorPalette;

/**
 * If a class can be painted, it shoukd implements this class
 */
public interface IColorable {

	/**
	 * This function sets palette of IColorable object
	 * 
	 * @param palette
	 */
	public void setPalette(ColorPalette palette);
}
