package mediator;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import fileio.repository.UpdateMediator;

public class Updater {

	private UpdateMediator mediator;
	private Runnable PROGRAM;

	public Updater() {
		mediator = new UpdateMediator();
		setUpRun();
	}

	/**
	 * In every 10 seconds, get the new coin values from update mediator and update
	 * the pool's updateable objects
	 */
	public void RUN() {
		try {
			Thread.EXECUTOR = Executors.newScheduledThreadPool(1);
			Thread.EXECUTOR.scheduleAtFixedRate(PROGRAM, 0, 10, TimeUnit.SECONDS);
		} catch (RuntimeException e) {
			Thread.EXECUTOR.shutdown();
			UpdatePool.POOL.clear();
		}
	}

	private void setUpRun() {
		PROGRAM = new Runnable() {
			@Override
			public void run() {
				mediator.updateValues();
				UpdatePool.UPDATE();
			}
		};
	}
}
