package view.decorator;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;

import mediator.WalletListMediator;
import view.AppView;
import view.WalletView;
import view.list.WalletEntityList;

/**
 * This class fills wallet entity list by crypto wallet entities
 */
public class CryptoWalletListDecorator extends JListDecorator {

	public CryptoWalletListDecorator(AppView view) {
		super(view);
	}

	@Override
	public void set() {
		update();
		view.setList(this.list);

	}

	@Override
	public void update() {
		WalletView viewTemp = (WalletView) this.view; // call wallet list mediator
		WalletListMediator mediator = new WalletListMediator(viewTemp.getCryptoId(), viewTemp.getBankId());
		DefaultListModel<JLabel> listModel = mediator.getCryptoWalletList(); // get crypto list
		this.list = new WalletEntityList(listModel); // set list
	}

}
