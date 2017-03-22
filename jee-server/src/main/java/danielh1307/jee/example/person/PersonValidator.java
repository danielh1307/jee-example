package danielh1307.jee.example.person;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import danielh1307.jee.example.exceptions.ValidationException;
import danielh1307.jee.example.util.LogMethodInterceptor;

@Stateless
@Local
public class PersonValidator {

	/**
	 * Here we do some business validations for person, which is not done by the
	 * entity itself.
	 * 
	 */
	@Interceptors(LogMethodInterceptor.class)
	public void validate(Person person) throws ValidationException {
		if (person.getVorname().equals(person.getNachname())) {
			throw new ValidationException("vorname and nachname must be different for person [" + person + "]");
		}
	}

}
