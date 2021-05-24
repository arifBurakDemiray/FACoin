package configuration;

import controller.Consumable;
import controller.IConsumable;
import exception.FileReadException;
import fileio.repository.BaseRepository;
import httpio.repository.HttpRepository;
import mediator.LoginMediator;
import mediator.Updater;
import view.AppWindow;

/**
 * 
 * This class configures services of the program
 *
 */
public class ConfigurationManager {

	public ConfigurationManager() {

	}

	/**
	 * This function initializes a thread that is going to update coin values every
	 * 10 second and update elements which are in {@link mediator.UpdatePool}
	 */
	protected void configureUpdateThread() {
		Updater updater = new Updater();
		updater.RUN();
	}

	/**
	 * This function initialises app window for the system to use {@link Startup}
	 */
	protected void configureAppWindow() {
		IConsumable window = new AppWindow();
		window.supressNotUsed();

	}

	/**
	 * This function initialises databases of the system {@link Startup}
	 * @throws FileReadException 
	 */
	protected void configureDatabases() throws FileReadException {
		BaseRepository br = new BaseRepository();
		br.initDatabase();
		HttpRepository httpRepo = new HttpRepository();
		httpRepo.initHttpDatabase();

	}

	/**
	 * This function starts loging mediator which program starts with login
	 * {@link mediator.LoginMediator}
	 */
	protected void configureStart() {
		Consumable loginMediator = new LoginMediator();
		loginMediator.supressNotUsed();
	}
}
