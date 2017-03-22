package danielh1307.jee.example.lcs;

import danielh1307.jee.example.util.BaseEntity;

public class LifeCycleChangeEvent<T extends BaseEntity> {
	
	private T baseEntity;
	
	public LifeCycleChangeEvent(T entity) {
		this.baseEntity = entity;
	}
	
	public T getEntity() {
		return baseEntity;
	}

}
