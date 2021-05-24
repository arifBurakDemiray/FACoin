package view.decorator;

import view.list.List;

/**
 * This class sorts a list decorator by ascending order
 */
public class AscendingOrderListDecorator extends SortListDecorator {

	public AscendingOrderListDecorator(JListDecorator decorator) {
		super(decorator);
	}

	@Override
	public void set() {
		decorator.update(); // update values
		List temp = sort(decorator.list, new AscendingComparor()); // sort values
		decorator.view.setList(temp); // set list
	}
}
