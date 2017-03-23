package danielh1307.jee.example.vehicle;

import javax.ejb.EJB;
import javax.enterprise.event.Observes;

import danielh1307.jee.example.address.Address;
import danielh1307.jee.example.enums.LifeCycleType;
import danielh1307.jee.example.lcs.LifeCycleChangeEvent;
import danielh1307.jee.example.lcs.VehicleLcsChange;
import danielh1307.jee.example.util.LifeCycleChanger;

/**
 * Handles the life cycle changes of {@link Address}.
 * 
 *
 */
public class VehicleLcsHandler {
	
	@EJB
	private LifeCycleChanger lifeCycleChanger;
	
	/**
	 * 
	 * @param e the {@link LifeCycleChangeEvent} which was triggered because of the life cycle change.
	 */
	public void observe(@Observes @VehicleLcsChange LifeCycleChangeEvent<Vehicle> e) {
		Vehicle v = e.getEntity();
		
		if (v.getLifeCycle() != LifeCycleType.INAKTIV) {
			// no action needed, only when life cycle gets inactive
			return;
		}
		
		// check if the related person has any other active vehicles ...
		boolean anyOtherVehicleActive = false;
		for (Vehicle v1 : v.getOwner().getVehicles()) {
			if (v1.getLifeCycle() == LifeCycleType.AKTIV) {
				anyOtherVehicleActive = true;
			}
		}
		
		// ... if no, we also set the person to inactive
		if (!anyOtherVehicleActive) {
			lifeCycleChanger.inactivatePerson(v.getOwner().getId());
		}
	}

}
