package danielh1307.jee.example.journal;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import danielh1307.jee.example.address.Address;
import danielh1307.jee.example.person.Person;

public class JournalHandlerFactory {
	
	private static Logger logger = LoggerFactory.getLogger(JournalHandlerFactory.class);

	public static <T> JournalHandler getJournalHandler(Class<T> clazz) {
		if (clazz.equals(Person.class)) {
			return new PersonJournalHandler();
		}
		if (clazz.equals(Address.class)) {
			return new AddressJournalHandler();
		}
		
		logger.warn("No JournalHandler configured for class " + clazz);
		return null;
	}
	
}
