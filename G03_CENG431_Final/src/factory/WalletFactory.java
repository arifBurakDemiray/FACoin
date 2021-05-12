package factory;

import java.util.Dictionary;

import factory.validator.ValidationResult;
import factory.validator.WalletValidator;
import fileio.CurrencyRepository;
import fileio.DatabaseResult;
import model.ICurrency;
import model.Wallet;
import model.WalletEntity;

public abstract class WalletFactory {

	public WalletFactory(){}
	public abstract Wallet createWallet(String id, Dictionary<String,String> params);
	protected WalletEntity createWalletEntity(String currencyId, String quantity){
		ValidationResult vr = WalletValidator.validateWalletEntity(currencyId, quantity);
		WalletEntity entityResult = null;
		if(!vr.isValid)
			return entityResult;
		DatabaseResult result = (new CurrencyRepository()).getCurrencyById(currencyId);
		Object resultObject = result.getObject();
		if(resultObject!=null){
			entityResult = new WalletEntity((ICurrency) resultObject,Float.valueOf(quantity));
		}
		return entityResult;
	}

}
