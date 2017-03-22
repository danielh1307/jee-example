package danielh1307.jee.example.journal;

import danielh1307.jee.example.util.BaseEntity;

public interface JournalHandler {

	public void journalizeUpdate(BaseEntity beforeEntity, BaseEntity afterEntity);
	
	public void journalizeNew(BaseEntity newEntity);
	
	public default String getEntityNewLog(BaseEntity newEntity) {
		return "New entity " + newEntity.getClass() + " persisted:  " + newEntity;
	}
	
	public default String getEntityUpdateLog(BaseEntity beforeEntity, BaseEntity newEntity) {
		return "Entity " + newEntity.getClass() + " updated.\nOld entity: " + beforeEntity + "\nUpdated entity: " + newEntity;
	}
}
