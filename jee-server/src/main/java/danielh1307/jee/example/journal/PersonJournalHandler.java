package danielh1307.jee.example.journal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import danielh1307.jee.example.person.Person;
import danielh1307.jee.example.util.BaseEntity;

public class PersonJournalHandler implements JournalHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonJournalHandler.class);

	@Override
	public void journalizeUpdate(BaseEntity beforeEntity, BaseEntity afterEntity) {
		Person beforePerson = (Person) beforeEntity;
		Person afterPerson = (Person) afterEntity;
		
		logger.info(getEntityUpdateLog(beforePerson, afterPerson));
	}

	@Override
	public void journalizeNew(BaseEntity newEntity) {
		Person newPerson = (Person) newEntity;
		logger.info(getEntityNewLog(newPerson));
	}

}
