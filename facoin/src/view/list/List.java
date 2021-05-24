package view.list;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;

/**
 * This class is modified JList<JLabel>. It has various additional functions for
 * JList component
 */
public abstract class List extends JList<JLabel> {

	private static final long serialVersionUID = 6644497569840427309L;
	private DefaultListModel<JLabel> listModel;

	public List() {
		listModel = new DefaultListModel<JLabel>();
	}

	public List(DefaultListModel<JLabel> list) {
		listModel = list;
	}

	public DefaultListModel<JLabel> getList() {
		return this.listModel;
	}

	protected void setList(DefaultListModel<String> list) {
		this.getList().clear();
		updateList(list);
	}

	/**
	 * Updates list content by given String typed DefaultListModel
	 * 
	 * @param list that has String content
	 */
	private void updateList(DefaultListModel<String> list) {
		this.addAll(convertToLabel(list));

	}

	/**
	 * This function converts a String list to JLabel list
	 * 
	 * @param list that is String typed
	 * @return JLabel version of the list
	 */
	private DefaultListModel<JLabel> convertToLabel(DefaultListModel<String> list) {
		DefaultListModel<JLabel> result = new DefaultListModel<JLabel>();
		int size = list.size();
		if (size < 1)
			return result;
		for (int i = 0; i < size; i++) {
			result.addElement(new JLabel(list.get(i)));
		}
		return result;
	}

	/**
	 * This function adds all given list to the List's list model
	 * 
	 * @param list
	 */
	private void addAll(DefaultListModel<JLabel> list) {
		int size = list.size();
		if (size < 1)
			return;
		for (int i = 0; i < size; i++) {
			this.getList().add(i, list.get(i));

		}
	}

}
