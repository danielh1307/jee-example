package danielh1307.jee.example.vehicle;

import javax.ejb.Remote;

import danielh1307.jee.example.enums.LifeCycleType;

/**
 * 
 * Interface for entity {@link Vehicle}.
 *
 */
@Remote
public interface VehicleService {

	/**
	 * Add a new vehicle.
	 * 
	 * @param v
	 *            the vehicle to add
	 * @return the vehicle which was added ({@link Vehicle#getId()} is set)
	 * @throws Exception
	 */
	public Vehicle addVehicle(Vehicle v) throws Exception;

	/**
	 * Updates an existing vehicle.
	 * 
	 * @param v
	 *            the updated vehicle
	 * @return the vehicle after the udpate
	 * @throws Exception
	 */
	public Vehicle updateVehicle(Vehicle v) throws Exception;

	/**
	 * Returns a specific vehicle.
	 * 
	 * @param id
	 *            the id of the vehicle
	 * @return the vehicle with the given id. If the vehicle does not exist,
	 *         null is returned.
	 */
	public Vehicle getVehicle(int id);

	/**
	 * Sets the life cycle state of a specific vehicle to
	 * {@link LifeCycleType#INAKTIV}
	 * 
	 * @param id
	 *            the id of the vehicle
	 * @return the vehicle with the new life cycle state
	 */
	public Vehicle inactivateVehicle(int id);

}
