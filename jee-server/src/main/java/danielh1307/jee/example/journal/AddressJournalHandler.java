package danielh1307.jee.example.journal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import danielh1307.jee.example.address.Address;
import danielh1307.jee.example.util.BaseEntity;

public class AddressJournalHandler implements JournalHandler{
	
	private static final Logger logger = LoggerFactory.getLogger(AddressJournalHandler.class);

	@Override
	public void journalizeUpdate(BaseEntity beforeEntity, BaseEntity afterEntity) {
		Address beforeAddress = (Address) beforeEntity;
		Address afterAddress = (Address) afterEntity;
		logger.info(getEntityUpdateLog(beforeAddress, afterAddress));

	}

	@Override
	public void journalizeNew(BaseEntity newEntity) {
		Address newAddress = (Address) newEntity;
		logger.info(getEntityNewLog(newAddress));

	}

}
