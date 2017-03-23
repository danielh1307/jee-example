package danielh1307.jee.example.person.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import danielh1307.jee.example.address.AddressValidator;
import danielh1307.jee.example.exceptions.ValidationException;
import danielh1307.jee.example.person.Person;
import danielh1307.jee.example.person.PersonService;
import danielh1307.jee.example.person.PersonValidator;
import danielh1307.jee.example.person.access.PersonAccess;
import danielh1307.jee.example.util.LifeCycleChanger;
import danielh1307.jee.example.util.LogMethodInterceptor;
import danielh1307.jee.example.util.MDCInterceptor;
import danielh1307.jee.example.util.access.GenericAccess;

/**
 * {@link TransactionAttributeType#REQUIRED} means a transaction is required and
 * created if there is none. If this service must be called with an existing
 * transaction, {@link TransactionAttributeType#MANDATORY} would be the correct
 * setting.
 *
 */
@Stateless(mappedName = "PersonService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors({ MDCInterceptor.class, LogMethodInterceptor.class })
public class PersonServiceImpl implements PersonService {

	private static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

	@EJB
	private PersonValidator personValidator;

	@EJB
	private AddressValidator addressValidator;

	@EJB
	private GenericAccess access;

	@EJB
	private PersonAccess personAccess;

	@EJB
	private LifeCycleChanger lifeCycleChanger;

	@Override
	public Person addPerson(Person person) throws Exception {
		try {
			validatePerson(person);
			return access.save(person);
		} catch (Exception ex) {
			logger.error("Error during addPerson", ex);
			throw ex;
		}
	}

	@Override
	public Person updatePerson(Person person) throws Exception {
		try {
			validatePerson(person);
			return access.update(person);
		} catch (Exception ex) {
			logger.error("Error during addPerson", ex);
			throw ex;
		}
	}

	@Override
	public Person getPerson(int id) {
		return personAccess.get(id);
	}

	@Override
	public void deletePerson(int id) {
		personAccess.delete(id);
	}

	@Override
	public Person inactivatePerson(int id) {
		return lifeCycleChanger.inactivatePerson(id);
	}

	private void validatePerson(Person p) throws ValidationException {
		personValidator.validate(p);
		if (p.getAdresse().isPresent()) {
			addressValidator.validate(p.getAdresse().get());
		}

	}

}
