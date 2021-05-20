package mediator;

import javax.swing.JLabel;
import controller.WalletController;
import enums.ECoins;
import exception.HttpRequestException;
import model.User;
import view.WalletView;
import view.decorator.BankWalletListDecorator;
import view.decorator.CryptoWalletListDecorator;
import view.decorator.DarkThemeDecorator;
import view.decorator.JListDecorator;
import view.decorator.ThemeDecorator;


public class WalletMediator {

	private User user;
	private WalletView view;
	private WalletController controller;
	private JListDecorator decorator;
	private boolean isBankView = false;
	private boolean isCryptoView = true;
	

	public WalletMediator(User user) {
		this.user = user;
		view = new WalletView(user.getCryptoWallet().getId(),user.getBankWallet().getId());
		decorator = new CryptoWalletListDecorator(view);
		UpdatePool.POOL.add(decorator);
		ThemeDecorator theme = new DarkThemeDecorator(view);
		controller = new WalletController(this);
	}

	public WalletView getView() {
		return view;
	}

	
	public void back() {
		view.setVisible(false);
		UpdatePool.POOL.clear();
		HomeMediator mediator = new HomeMediator(user);
	}	

	public void getSelectedCoinView() throws HttpRequestException {

		JLabel label = view.getListSelected();
		if(label==null)
		{
			return;
		}	
		String[] splittedTrade = label.getText().split("/");
		if(!ECoins.isCoin(splittedTrade[0]))
		{
			return;
		}
		
		String[] splittedItem = label.getText().split(":");
		view.setVisible(false);	
		CoinInfoMediator mediator = new CoinInfoMediator(splittedItem[0], user);
	
	}

	public void cryptoView() {
		if(!isCryptoView){
			UpdatePool.POOL.remove(decorator);
			decorator = new CryptoWalletListDecorator(this.view);
			UpdatePool.POOL.add(decorator);
			isCryptoView = true;
			isBankView = false;
		}
		
	}

	public void bankView() {
		if(!isBankView){
			UpdatePool.POOL.remove(decorator);
			decorator = new BankWalletListDecorator(this.view);
			UpdatePool.POOL.add(decorator);
			isBankView = true;
			isCryptoView = false; 
		}
	}

}