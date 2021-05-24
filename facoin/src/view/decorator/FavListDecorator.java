package view.decorator;

import service.FavListService;
import view.AppView;
import view.HomeView;

/**
 * This class sets coin list by favorites
 */
public class FavListDecorator extends JListDecorator {

	public FavListDecorator(AppView view) {
		super(view);
	}

	@Override
	public void set() {
		update();
		view.setList(this.list);

	}

	@Override
	public void update() {
		String userId = ((HomeView) this.view).getUserId(); // get id
		FavListService fls = new FavListService(userId); // call service
		this.list = fls.getList(); // and get list
	}
}
