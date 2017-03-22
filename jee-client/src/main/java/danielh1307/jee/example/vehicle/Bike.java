package danielh1307.jee.example.vehicle;

import java.io.StringWriter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import danielh1307.jee.example.enums.BikeTypeConverter;
import danielh1307.jee.example.util.BaseEntity;

@Entity
@DiscriminatorValue("B")
public class Bike extends Vehicle {

	private static final long serialVersionUID = 1L;

	/**
	 * Be careful here - the field must not be nullable if a common table for
	 * all subclasses is used
	 */
	@Column
	@Convert(converter = BikeTypeConverter.class)	
	@NotNull
	private BikeType type;

	protected Bike() {
		/**
		 * From JPA specification: The entity class must have a no-arg
		 * constructor. It may have other constructors as well. The no-arg
		 * constructor must be public or protected.
		 */
	}

	public Bike(BikeType type, String description) {
		super(description);
		this.type = type;
	}

	public BikeType getType() {
		return type;
	}
	
	@Override
	public String toString() {
		StringWriter writer = new StringWriter();
		writer.append("id: [" + id + "] ");
		writer.append("type: [" + type + "] ");
		writer.append("description: [" + getDescription() + "] ");
		return writer.toString();
	}

	@Override
	protected BaseEntity buildBeforeImage() {
		Bike b = new Bike(type, getDescription());
		b.id = id;
		return b;
	}

}
