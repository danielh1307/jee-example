package danielh1307.jee.example.person;

import java.io.StringWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import danielh1307.jee.example.address.Address;
import danielh1307.jee.example.util.BaseEntity;
import danielh1307.jee.example.util.LocalDateConverter;
import danielh1307.jee.example.vehicle.Vehicle;

@Entity
@Table(name = "Person")
public class Person extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String vorname;

	@Column(nullable = false)
	private String nachname;

	@Column(nullable = false)
	@Convert(converter = LocalDateConverter.class)
	private LocalDate geburtsdatum;

	/**
	 * In a One-to-one relation, it should be OK to eager fetch it. If the fetch
	 * type is LAZY, it is not enough to just call getAdresse() but you must
	 * call a getter of the {@link Address} itself.
	 */
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ADDRESS_ID")
	private Address adresse;

	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	private List<Vehicle> vehicles;

	protected Person() {
		/**
		 * From JPA specification: The entity class must have a no-arg
		 * constructor. It may have other constructors as well. The no-arg
		 * constructor must be public or protected.
		 */
	}

	private Person(PersonBuilder builder) {
		super();

		// here we directly validate the input parameters
		// in other classes, we use the bean validation which
		// is not validated during object creation
		testParamNotNullNotEmpty(builder.vorname, "vorname");
		testParamNotNullNotEmpty(builder.nachname, "nachname");
		testParamNotNull(builder.geburtsdatum, "geburtsdatum");

		this.vorname = builder.vorname;
		this.nachname = builder.nachname;
		this.geburtsdatum = builder.geburtsdatum;
		vehicles = new ArrayList<>();
	}

	public static class PersonBuilder {
		private String vorname;
		private String nachname;
		private LocalDate geburtsdatum;

		public PersonBuilder vorname(String vorname) {
			this.vorname = vorname;
			return this;
		}

		public PersonBuilder nachname(String nachname) {
			this.nachname = nachname;
			return this;
		}

		public PersonBuilder geburtsdatum(LocalDate geburtsdatum) {
			this.geburtsdatum = geburtsdatum;
			return this;
		}

		public Person build() {
			return new Person(this);
		}
	}

	public String getVorname() {
		return vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public LocalDate getGeburtsdatum() {
		return geburtsdatum;
	}

	/**
	 * 
	 * @return the address which belongs to this person
	 */
	public Optional<Address> getAdresse() {
		return Optional.ofNullable(adresse);
	}

	/**
	 * 
	 * @param newAddress the address which belongs to this person
	 */
	public void setAdresse(Address newAddress) {
		testParamNotNull(newAddress, "adresse");

		// since there is a bi-directional mapping between person and address,
		// we have to make sure the given address does not already belong to a different person
		if (newAddress.getPerson() != null) {
			if (!this.equals(newAddress.getPerson())) {
				throw new RuntimeException(
						"The given address belongs to a different person: [" + newAddress.getPerson() + "].");
			}
			adresse = newAddress;
		} else {
			adresse = newAddress;
			adresse.setPerson(this);
		}
	}

	/**
	 * Removes the current address from this person.
	 */
	public void removeAdresse() {
		if (adresse != null) {
			// since there is a bi-directional mapping between person and address,
			// we also have to remove this person from the address
			Address oldAddress = adresse;
			adresse = null;
			oldAddress.removePerson();
		}
	}

	public void addVehicle(Vehicle v) {
		if (vehicles.contains(v)) {
			// if the vehicle is already in the list, we immediately return
			return;
		}
		vehicles.add(v);

		// we have to maintain the bi-directional relation
		v.setOwner(this);
	}

	public void removeVehicle(Vehicle v) {
		if (!vehicles.contains(v)) {
			// if the vehicle is not in the list, we cannot remove it
			return;
		}

		vehicles.remove(v);

		// we have to maintain the bi-directional mapping
		v.setOwner(null);
	}

	/**
	 * 
	 * @return an unmodifiable list of the vehicles belonging to this person.
	 *         Any change of the returned list will lead to an
	 *         {@link UnsupportedOperationException}, so don't do it.
	 */
	public List<Vehicle> getVehicles() {
		return Collections.unmodifiableList(vehicles);
	}

	@Override
	public String toString() {
		StringWriter writer = new StringWriter();
		writer.append("id: [" + id + "] ");
		writer.append("vorname: [" + vorname + "] ");
		writer.append("nachname: [" + nachname + "] ");
		writer.append("geburtsdatum: [" + geburtsdatum + "]");
		return writer.toString();
	}

	@Override
	protected Person buildBeforeImage() {
		// you do not have to build associated BaseEntity objects since they
		// have their own buildBeforeImage method
		Person p = new PersonBuilder().vorname(vorname).nachname(nachname).geburtsdatum(geburtsdatum).build();
		p.id = id;
		return p;
	}
}
