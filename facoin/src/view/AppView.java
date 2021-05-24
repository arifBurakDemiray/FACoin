package view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import view.list.List;

/**
 * Abstrac class for the View classes
 */
public abstract class AppView extends JPanel implements IColorable {

	private static final long serialVersionUID = -7079744109850014194L;

	public AppView() {
	}

	/**
	 * Sets the list of a view
	 * 
	 * @param list
	 */
	public abstract void setList(List list);

	/**
	 * Sets the label of a view
	 * 
	 * @param label
	 */
	public void setLabel(JLabel label) {

	}

	/**
	 * Gets the list of a view
	 * 
	 * @return list of a view
	 */
	public abstract List getList();
}
