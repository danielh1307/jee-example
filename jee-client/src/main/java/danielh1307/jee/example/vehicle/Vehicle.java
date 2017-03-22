package danielh1307.jee.example.vehicle;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import danielh1307.jee.example.person.Person;
import danielh1307.jee.example.util.BaseEntity;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "VEHICLE_TYPE")
public abstract class Vehicle extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	@NotNull
	@Size(min = 1)
	private String description;

	protected Vehicle() {
		/**
		 * From JPA specification: The entity class must have a no-arg
		 * constructor. It may have other constructors as well. The no-arg
		 * constructor must be public or protected.
		 */
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "OWNER_ID")
	private Person owner;

	public Vehicle(String description) {
		super();

		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person newOwner) {
		if (sameAsFormer(newOwner)) {
			// we do not set the same owner twice
			return;
		}

		// so the new owner differs from the existing one, therefore we have to
		// cancel the relation between the existing owner and the vehicle
		if (owner != null) {
			owner.removeVehicle(this);
		}
		
		owner = newOwner;

		// we have to maintain the bi-directional relation
		if (owner != null) {
			owner.addVehicle(this);
		}
	}

	private boolean sameAsFormer(Person newOwner) {
		return owner == null ? newOwner == null : owner.equals(newOwner);
	}

}
