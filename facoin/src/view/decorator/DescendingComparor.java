package view.decorator;

import java.util.Comparator;

import javax.swing.JLabel;

/**
 * This class compares two JLabel's for descending orders
 */
public class DescendingComparor implements Comparator<Object> {

	@Override
	public int compare(Object o1, Object o2) {
		JLabel temp1 = (JLabel) o1;
		JLabel temp2 = (JLabel) o2;
		Double val1 = SortHelper.value(temp1);
		Double val2 = SortHelper.value(temp2);
		int result = 1;
		if (val1 > val2) { // if val1 greater than val2 return result -1
			result = -1;
		}
		return result;
	}
}
