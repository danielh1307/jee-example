package danielh1307.jee.example.vehicle;

import javax.ejb.EJB;
import javax.enterprise.event.Observes;

import danielh1307.jee.example.enums.LifeCycleType;
import danielh1307.jee.example.lcs.LifeCycleChangeEvent;
import danielh1307.jee.example.lcs.VehicleLcsChange;
import danielh1307.jee.example.util.LifeCycleChanger;

public class VehicleLcsHandler {
	
	@EJB
	private LifeCycleChanger lifeCycleChanger;
	
	public void observe(@Observes @VehicleLcsChange LifeCycleChangeEvent<Vehicle> e) {
		Vehicle v = e.getEntity();
		
		// check if the related person has any other active vehicles
		boolean anyOtherVehicleActive = false;
		for (Vehicle v1 : v.getOwner().getVehicles()) {
			if (v1.getLifeCycle() == LifeCycleType.AKTIV) {
				anyOtherVehicleActive = true;
			}
		}
		
		if (!anyOtherVehicleActive) {
			lifeCycleChanger.inactivatePerson(v.getOwner().getId());
		}
	}

}
