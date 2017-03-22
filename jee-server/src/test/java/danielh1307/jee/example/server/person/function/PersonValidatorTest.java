package danielh1307.jee.example.server.person.function;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import danielh1307.jee.example.exceptions.ValidationException;
import danielh1307.jee.example.person.Person;
import danielh1307.jee.example.person.PersonValidator;

public class PersonValidatorTest {

	private PersonValidator validator;

	@Before
	public void init() {
		validator = new PersonValidator();
	}

	@Test
	public void testValidPerson() throws ValidationException {
		Person p = new Person.PersonBuilder().vorname("Max").nachname("Meier").geburtsdatum(LocalDate.of(2000, 1, 1)).build();
		validator.validate(p);
	}

	@Test
	public void testInvalidPerson() {
		Person p = new Person.PersonBuilder().vorname("Max").nachname("Max").geburtsdatum(LocalDate.of(2000, 1, 1)).build();
		try {
			validator.validate(p);
			Assert.fail("Expected exception not thrown for person [" + p + "]");
		} catch (ValidationException ex) {
			// exception is expected
		}
	}

}
