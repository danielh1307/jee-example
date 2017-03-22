package danielh1307.jee.example.client.address;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Test;

import danielh1307.jee.example.address.Address;
import danielh1307.jee.example.address.Address.AddressBuilder;
import junit.framework.Assert;

public class AddressTest {

	@Test
	public void testCreateInvalidAddress() throws Exception {
		Address a = new AddressBuilder().strasse("Test").build();

		// it is possible to create such an object, but validation should lead
		// to errors
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Address>> constraintViolations = validator.validate(a);
		Assert.assertEquals(3, constraintViolations.size());
	}

}
