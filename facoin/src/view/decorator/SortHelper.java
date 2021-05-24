package view.decorator;

import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;

public class SortHelper {

	/**
	 * This function gets a value from JLabel
	 * 
	 * @param label
	 * @returns parsed value from JLabel
	 */
	protected static Double value(JLabel label) {
		String[] splitted = label.getText().split("%"); // get percent value
		String[] splitted1 = splitted[0].split("\\("); // get percent value
		Double result = Double.parseDouble(splitted1[1].replaceAll(",", "."));
		if (label.getForeground() == Color.RED) // if given label's froregrond red means decreasing it should be at the
												// low level
			return result * (-1);
		else
			return result;
	}

	/**
	 * This function converts an object array that is Jlabel array into default list
	 * model
	 * 
	 * @param listA of JLabel list
	 * @returns default list model for given list
	 */
	protected static DefaultListModel<JLabel> convert(Object[] listA) {
		DefaultListModel<JLabel> tempL = new DefaultListModel<JLabel>();
		for (int i = 0; i < listA.length; i++) {
			tempL.addElement((JLabel) listA[i]);
		}
		return tempL;
	}
}
