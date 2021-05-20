package view.decorator;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;

import mediator.WalletListMediator;
import view.AppView;
import view.WalletView;
import view.list.WalletEntityList;

public class CryptoWalletListDecorator extends JListDecorator{

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
		WalletView viewTemp = (WalletView) this.view;
		WalletListMediator mediator = new WalletListMediator(viewTemp.getCryptoId(),viewTemp.getBankId());
		DefaultListModel<JLabel> listModel = mediator.getCryptoWalletList();
		this.list = new WalletEntityList(listModel);
	}

}
