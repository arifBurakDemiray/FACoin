package mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * UpdatePool holds the updateable objects of the system in the pool to update
 * them in every 10 seconds
 *
 */
public class UpdatePool {

	protected static List<IUpdatable> POOL = new ArrayList<IUpdatable>();

	protected static void UPDATE() {
		for (IUpdatable dec : POOL) {
			dec.set();
		}
	}
}
