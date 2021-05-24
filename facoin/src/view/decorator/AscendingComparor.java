package view.decorator;

import java.util.Comparator;

import javax.swing.JLabel;

/**
 * This class compares two JLabels for ascending order
 */
public class AscendingComparor implements Comparator<Object> {

	@Override
	public int compare(Object o1, Object o2) {
		JLabel temp1 = (JLabel) o1;
		JLabel temp2 = (JLabel) o2;
		Double val1 = SortHelper.value(temp1); //get JLabel's values
		Double val2 = SortHelper.value(temp2);
		int result = -1;
		if (val1 > val2) { //if val1 greater than val2
			result = 1;
		}
		return result;
	}

}
