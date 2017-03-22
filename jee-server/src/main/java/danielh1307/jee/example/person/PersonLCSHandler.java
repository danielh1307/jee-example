package danielh1307.jee.example.person;

import javax.ejb.EJB;
import javax.enterprise.event.Observes;

import danielh1307.jee.example.lcs.LifeCycleChangeEvent;
import danielh1307.jee.example.lcs.PersonLcsChange;
import danielh1307.jee.example.util.LifeCycleChanger;

public class PersonLCSHandler {

	@EJB
	private LifeCycleChanger lifeCycleChanger;

	public void observe(@Observes @PersonLcsChange LifeCycleChangeEvent<Person> e) {
		Person p = e.getEntity();

		// if the state of a person gets inactive, the address must also get
		// inactive
		if (p.getAdresse().isPresent()) {
			lifeCycleChanger.inactivateAddress(p.getAdresse().get().getId());
		}
	}

}
