package danielh1307.jee.example.lcs;

import danielh1307.jee.example.util.BaseEntity;

/**
 * This is an event class which is always fired when the life cycle state
 * of an object changes.
 *
 * @param <T> the type of the {@link BaseEntity} whose life cycle state changed
 */
public class LifeCycleChangeEvent<T extends BaseEntity> {
	
	private T baseEntity;
	
	public LifeCycleChangeEvent(T entity) {
		this.baseEntity = entity;
	}
	
	/**
	 * 
	 * @return the entity whose life cycle state changed
	 */
	public T getEntity() {
		return baseEntity;
	}

}
