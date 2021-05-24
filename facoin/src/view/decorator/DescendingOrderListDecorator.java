package view.decorator;

import view.list.List;

/**
 * This class sorts a list decorator by descending order
 */
public class DescendingOrderListDecorator extends SortListDecorator {

	public DescendingOrderListDecorator(JListDecorator decorator) {
		super(decorator);
	}

	@Override
	public void set() {
		decorator.update(); // update decorator
		List temp = sort(decorator.list, new DescendingComparor()); // sort descending order
		decorator.view.setList(temp);// set list

	}
}
