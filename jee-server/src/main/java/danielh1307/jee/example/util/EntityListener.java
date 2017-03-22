package danielh1307.jee.example.util;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

import danielh1307.jee.example.journal.JournalHandler;
import danielh1307.jee.example.journal.JournalHandlerFactory;

public class EntityListener {

	@PostPersist
	public void onPostPersist(Object o) {
		BaseEntity newEntity = (BaseEntity) o;

		// we know this is a new entity

		// journalize it
		JournalHandler journalHandler = JournalHandlerFactory.getJournalHandler(newEntity.getClass());
		if (journalHandler != null) {
			journalHandler.journalizeNew(newEntity);
		}

		// TODO: MRS
	}

	@PostUpdate
	public void onPostUpdate(Object o) {
		BaseEntity changedEntity = (BaseEntity) o;
		BaseEntity beforeImageEntity = changedEntity.getBeforeImage();

		// we know this is an updated entity

		// journalize it
		JournalHandler journalHandler = JournalHandlerFactory.getJournalHandler(changedEntity.getClass());
		if (journalHandler != null) {
			journalHandler.journalizeUpdate(beforeImageEntity, changedEntity);
		}

		// TODO: MRS
	}

	@PostRemove
	public void onPostRemove(Object o) {
		// this is called after the entity has been removed
	}

}
