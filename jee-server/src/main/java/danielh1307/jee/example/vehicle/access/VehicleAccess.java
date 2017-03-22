package danielh1307.jee.example.vehicle.access;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import danielh1307.jee.example.util.BaseEntity;
import danielh1307.jee.example.util.access.GenericAccess;
import danielh1307.jee.example.vehicle.Vehicle;

@Local
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class VehicleAccess {
	
	@EJB
	private GenericAccess access;
	
	public List<Vehicle> getVehiclesByPersonId(int personId) {		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("personId", personId);
		List<BaseEntity> baseEntityList = access.multiResultQuery("SELECT v FROM Vehicle v WHERE v.owner.id LIKE :personId", parameters);
		List<Vehicle> vehicleList = new ArrayList<>();
		for (BaseEntity b : baseEntityList) {
			vehicleList.add((Vehicle) b);
		}
		return vehicleList;
	}

}
