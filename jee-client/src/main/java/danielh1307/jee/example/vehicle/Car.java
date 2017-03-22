package danielh1307.jee.example.vehicle;

import java.io.StringWriter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@DiscriminatorValue("C")
public class Car extends Vehicle {

	private static final long serialVersionUID = 1L;

	/**
	 * Be careful here - the field must not be nullable if a common table for
	 * all subclasses is used
	 */
	@Column
	@NotNull
	@Size(min = 1)
	private String modell;

	protected Car() {
		/**
		 * From JPA specification: The entity class must have a no-arg
		 * constructor. It may have other constructors as well. The no-arg
		 * constructor must be public or protected.
		 */
	}

	public Car(String modell, String description) {
		super(description);
		this.modell = modell;
	}

	public String getModell() {
		return modell;
	}

	@Override
	public String toString() {
		StringWriter writer = new StringWriter();
		writer.append("id: [" + id + "] ");
		writer.append("model: [" + modell + "] ");
		writer.append("description: [" + getDescription() + "] ");
		return writer.toString();
	}

	@Override
	protected Car buildBeforeImage() {
		Car c = new Car(modell, getDescription());
		c.id = id;
		return c;
	}
}
