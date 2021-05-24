package view;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * This class renders a JList by JLabel's attributes
 */
public class JListRenderer extends JLabel implements ListCellRenderer<JLabel> {

	private static final long serialVersionUID = -3121097196832501690L;

	@Override
	public Component getListCellRendererComponent(JList<? extends JLabel> list, JLabel label, int index,
			boolean isSelected, boolean cellHasFocus) {

		setText(label.getText()); // set list cell's text
		setForeground(label.getForeground()); // set foreground of list cell
		if (isSelected) {// if cell selected make it black
			setBackground(Color.BLACK);
			setOpaque(true); // and show background
		} else {
			setOpaque(false); // else hide background
		}
		return this;
	}

}