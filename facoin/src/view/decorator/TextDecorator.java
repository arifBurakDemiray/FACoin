package view.decorator;

import javax.swing.JLabel;
import service.JLabelService;
import view.AppView;

/**
 * This class updates label for CoinInfoView
 */
public class TextDecorator extends Decorator {

	private AppView view;
	private String coinName;
	private String banknoteName;

	public TextDecorator(AppView view, String coinName, String banknoteName) {
		this.view = view;
		this.coinName = coinName;
		this.banknoteName = banknoteName;
		set();
	}

	@Override
	public void set() {
		update();
	}

	public void update() {
		JLabelService service = new JLabelService(); // call label service
		JLabel label = service.getCoinLabel(coinName, banknoteName); // get trading pair info
		this.view.setLabel(label); // and set label of the view
	}

}
