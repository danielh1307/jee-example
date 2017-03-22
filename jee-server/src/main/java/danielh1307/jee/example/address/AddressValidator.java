package danielh1307.jee.example.address;

import java.util.Set;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import danielh1307.jee.example.exceptions.ValidationException;
import danielh1307.jee.example.util.LogMethodInterceptor;

@Local
@Stateless
public class AddressValidator {
	
	/**
	 * Here we do the bean validation.
	 */
	@Interceptors(LogMethodInterceptor.class)
	public void validate(Address a) throws ValidationException {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Address>> constraintViolations = validator.validate(a);
		if (!constraintViolations.isEmpty()) {
			throw new ValidationException(constraintViolations.iterator().next().getMessage());
		}
	}

}
