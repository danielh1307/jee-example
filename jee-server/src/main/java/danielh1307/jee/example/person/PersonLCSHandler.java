package danielh1307.jee.example.person;

import javax.ejb.EJB;
import javax.enterprise.event.Observes;

import danielh1307.jee.example.enums.LifeCycleType;
import danielh1307.jee.example.lcs.LifeCycleChangeEvent;
import danielh1307.jee.example.lcs.PersonLcsChange;
import danielh1307.jee.example.util.LifeCycleChanger;

/**
 * Handles the life cycle changes of {@link Person}.
 *
 */
public class PersonLCSHandler {

	@EJB
	private LifeCycleChanger lifeCycleChanger;

	/**
	 * 
	 * @param e the {@link LifeCycleChangeEvent} which was triggered because of the life cycle change.
	 */
	public void observe(@Observes @PersonLcsChange LifeCycleChangeEvent<Person> e) {
		Person p = e.getEntity();

		// if the state of a person gets inactive, the address must also get inactive
		if (p.getLifeCycle() == LifeCycleType.INAKTIV && p.getAdresse().isPresent()) {
			lifeCycleChanger.inactivateAddress(p.getAdresse().get().getId());
		}
	}

}
