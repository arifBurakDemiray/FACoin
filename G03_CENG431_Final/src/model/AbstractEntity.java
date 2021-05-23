package model;

/**
 * This class is the abstract entity class for all models of the system It
 * stores Id info for them
 */
public abstract class AbstractEntity {
	private final String id;

	public AbstractEntity() {
		this.id = "";
	}

	public AbstractEntity(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}
}