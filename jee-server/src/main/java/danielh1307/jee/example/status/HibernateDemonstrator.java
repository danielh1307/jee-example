package danielh1307.jee.example.status;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import danielh1307.jee.example.address.Address;
import danielh1307.jee.example.person.Person;
import danielh1307.jee.example.person.access.PersonAccess;

@RequestScoped
public class HibernateDemonstrator {

	private static final Logger logger = LoggerFactory.getLogger(HibernateDemonstrator.class);

	@EJB
	private PersonAccess personAccess;

	public void demonstrateHibernate(int personId) {
		logger.info("We are demonstrating some featuers from Hibernate");
		logger.info("We start by loading two times the existing person with id [" + personId + "] in p1 and p2");
		Person p1 = personAccess.get(personId);
		Person p2 = personAccess.get(personId);
		logger.info("p1 == p2: " + (p1 == p2));
		logger.info("p1.equals(p2): " + p1.equals(p2));
		logger.info("Now we are changing p2 by giving it a new addres a1");
		Address a1 = new Address.AddressBuilder().strasse("x").hausnummer("x").ort("x").plz("x").build();
		p2.setAdresse(a1);
		logger.info("ID of new address is [" + p2.getAdresse().get().getId() + "]");
		logger.info("We change the hausnummer in the address to 18");
		p2.getAdresse().get().setHausnummer("18");
		logger.info("See p1 and p2 are still the same objects and they are still equal");
		logger.info("p1 == p2: " + (p1 == p2));
		logger.info("p1.equals(p2): " + p1.equals(p2));

		logger.info("Now we load the person with id [" + personId + "] again to p3");
		Person p3 = personAccess.get(personId);
		logger.info("p1 == p3: " + (p1 == p3));
		logger.info("p2 == p3: " + (p2 == p3));
		logger.info("p1.equals(p3): " + p1.equals(p3));
		logger.info("p2 equals(p3): " + p2.equals(p3));
		logger.info("ID of address of p3 is " + p3.getAdresse().get().getId() + " and hausnummer is "
				+ p3.getAdresse().get().getHausnummer());
		logger.info(
				"So we already see the new address object in p3 although it was set on p2 (but p2 and p3 are the same objects)");
		logger.info("Even the address object a1 and the address of p3 is the same");
		logger.info("a1 == p3.getAddress(): " + (a1 == p3.getAdresse().get()));
		logger.info("a1.equals(p3.getAddress()): " + a1.equals(p3.getAdresse().get()));
		logger.info("Now in the end the new address is persisted and has finally got an id");
	}

}
