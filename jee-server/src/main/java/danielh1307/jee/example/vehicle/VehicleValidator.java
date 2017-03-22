package danielh1307.jee.example.vehicle;

import java.time.LocalDate;

import javax.ejb.Local;
import javax.ejb.Stateless;

import danielh1307.jee.example.exceptions.ValidationException;
import danielh1307.jee.example.vehicle.Car;
import danielh1307.jee.example.vehicle.Vehicle;

@Local
@Stateless
public class VehicleValidator {

	public void validateVehicle(Vehicle v) throws ValidationException {
		if (!(v instanceof Car)) {
			return;
		}
		
		LocalDate current = LocalDate.now();
		LocalDate birthday = v.getOwner().getGeburtsdatum();
		
		// TODO: there might be an easier way to calculate this
		int yearsBetween = current.getYear() - birthday.getYear();
		if (current.getMonthValue() < birthday.getMonthValue()) {
			yearsBetween--;
		} else if (current.getMonthValue() == birthday.getMonthValue() && current.getDayOfMonth() < birthday.getDayOfMonth()) {
			yearsBetween--;
		}
		
		if (yearsBetween < 18) {
			throw new ValidationException("Owner of a car must not be younger than 18, currently: [" + yearsBetween + "]");
		}
	}
	
}
