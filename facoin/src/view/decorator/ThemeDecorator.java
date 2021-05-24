package view.decorator;

import view.IColorable;

/**
 * This decorator updates color of an IColorable object
 */
public abstract class ThemeDecorator extends Decorator {

	protected IColorable view;

	public ThemeDecorator(IColorable view) {
		this.view = view;
		set(); // update
	}
}
