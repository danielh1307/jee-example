package danielh1307.jee.example.vehicle;

import javax.ejb.Remote;

@Remote
public interface VehicleService {

	public Vehicle addVehicle(Vehicle v) throws Exception;
	
	public Vehicle updateVehicle(Vehicle v) throws Exception;
	
	public Vehicle getVehicle(int id);
	
	public Vehicle inactivateVehicle(int id);
	
}
