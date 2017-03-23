package danielh1307.jee.example.person;

import javax.ejb.Remote;

import danielh1307.jee.example.enums.LifeCycleType;

/**
 * 
 * Interface for entity {@link Person}.
 *
 */
@Remote
public interface PersonService {

	/**
	 * Add a new person.
	 * 
	 * @param person
	 *            the person to add
	 * @return the person which was added ({@link Person#getId()} is set)
	 * @throws Exception
	 */
	public Person addPerson(Person person) throws Exception;

	/**
	 * Updates an existing person.
	 * 
	 * @param person
	 *            the updated person
	 * @return the person after the update
	 * @throws Exception
	 */
	public Person updatePerson(Person person) throws Exception;

	/**
	 * Returns a specific person.
	 * 
	 * @param id
	 *            the id of the person
	 * @return the person with the given id. If the person does not exist, null
	 *         is returned.
	 */
	public Person getPerson(int id);

	/**
	 * Deletes the person with the given id from the database.
	 * 
	 * @param id
	 *            the id of the person which has to be deleted
	 */
	public void deletePerson(int id);

	/**
	 * Sets the life cycle state of a specific person to
	 * {@link LifeCycleType#INAKTIV}
	 * 
	 * @param id
	 *            the id of the person
	 * @return the person with the new life cycle state
	 */
	public Person inactivatePerson(int id);

}
