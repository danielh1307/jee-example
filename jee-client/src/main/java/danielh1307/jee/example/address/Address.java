package danielh1307.jee.example.address;

import java.io.StringWriter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import danielh1307.jee.example.person.Person;
import danielh1307.jee.example.util.BaseEntity;

@Entity
@Table(name = "Address")
public class Address extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@OneToOne(mappedBy = "adresse", fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
	private Person person;

	@Column(nullable = false)
	@NotNull
	@Size(min = 1)
	private String strasse;

	@Column(nullable = false)
	@NotNull
	@Size(min = 1)
	private String hausnummer;

	@Column(nullable = false)
	@NotNull
	@Size(min = 1)
	private String plz;

	@Column(nullable = false)
	@NotNull
	@Size(min = 1)
	private String ort;

	protected Address() {
		/**
		 * From JPA specification: The entity class must have a no-arg
		 * constructor. It may have other constructors as well. The no-arg
		 * constructor must be public or protected.
		 */
	}

	private Address(AddressBuilder builder) {
		super();

		strasse = builder.strasse;
		hausnummer = builder.hausnummer;
		plz = builder.plz;
		ort = builder.ort;
	}

	public static class AddressBuilder {
		private String strasse;
		private String hausnummer;
		private String plz;
		private String ort;

		public AddressBuilder strasse(String strasse) {
			this.strasse = strasse;
			return this;
		}

		public AddressBuilder hausnummer(String hausnummer) {
			this.hausnummer = hausnummer;
			return this;
		}

		public AddressBuilder plz(String plz) {
			this.plz = plz;
			return this;
		}

		public AddressBuilder ort(String ort) {
			this.ort = ort;
			return this;
		}

		public Address build() {
			return new Address(this);
		}
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getHausnummer() {
		return hausnummer;
	}

	public void setHausnummer(String hausnummer) {
		this.hausnummer = hausnummer;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person newPerson) {
		if (newPerson.getAdresse().isPresent()) {
			if (!this.equals(newPerson.getAdresse().get())) {
				throw new RuntimeException(
						"The given person belongs to a different address: [" + newPerson.getAdresse() + "]");
			}
			person = newPerson;
		} else {
			person = newPerson;
			person.setAdresse(this);
		}
	}

	public void removePerson() {
		if (person != null) {
			Person oldPerson = person;
			person = null;
			oldPerson.removeAdresse();
		}
	}

	@Override
	public String toString() {
		StringWriter writer = new StringWriter();
		writer.append("id: [" + id + "] ");
		writer.append("strasse: [" + strasse + "] ");
		writer.append("hausnummer: [" + hausnummer + "] ");
		writer.append("plz: [" + plz + "] ");
		writer.append("ort: [" + ort + "]");
		return writer.toString();
	}

	@Override
	protected Address buildBeforeImage() {
		Address a = new AddressBuilder().strasse(strasse).plz(plz).ort(ort).hausnummer(hausnummer).build();
		a.id = id;
		return a;
	}
}
