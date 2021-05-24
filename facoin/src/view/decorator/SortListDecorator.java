package view.decorator;

import java.util.Arrays;
import java.util.Comparator;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;

import view.list.CoinList;
import view.list.List;

/**
 * This class sorts a list decorator
 */
public abstract class SortListDecorator extends Decorator{

	protected JListDecorator decorator;
	
	public SortListDecorator(JListDecorator decorator) {
		this.decorator = decorator;
		set();
	}
	
	protected List sort(List list, Comparator<Object> comparor) {
		DefaultListModel<JLabel> viewList = list.getList(); //get decorator's list
		Object[] listArray = viewList.toArray();	//convert to array
		Arrays.sort(listArray, comparor); //sort
		List listTemp=new CoinList(SortHelper.convert(listArray)); //convert to default list model
		return listTemp; //and return
	}
}
