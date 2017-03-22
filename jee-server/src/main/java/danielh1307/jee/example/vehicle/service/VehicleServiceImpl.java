package danielh1307.jee.example.vehicle.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import danielh1307.jee.example.util.LifeCycleChanger;
import danielh1307.jee.example.util.LogMethodInterceptor;
import danielh1307.jee.example.util.MDCInterceptor;
import danielh1307.jee.example.util.access.GenericAccess;
import danielh1307.jee.example.vehicle.Vehicle;
import danielh1307.jee.example.vehicle.VehicleService;
import danielh1307.jee.example.vehicle.VehicleValidator;

@Stateless(mappedName = "VehicleService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Interceptors({ MDCInterceptor.class, LogMethodInterceptor.class })
public class VehicleServiceImpl implements VehicleService {

	private static Logger logger = LoggerFactory.getLogger(VehicleServiceImpl.class);

	@EJB
	VehicleValidator validator;

	@EJB
	GenericAccess access;

	@EJB
	LifeCycleChanger lifeCycleChanger;

	@Override
	public Vehicle addVehicle(Vehicle v) throws Exception {
		try {
			validator.validateVehicle(v);
			return access.save(v);
		} catch (Exception ex) {
			logger.error("Error during addVehicle", ex);
			throw ex;
		}
	}

	@Override
	public Vehicle updateVehicle(Vehicle v) throws Exception {
		try {
			validator.validateVehicle(v);
			return access.update(v);
		} catch (Exception ex) {
			logger.error("Error during addVehicle", ex);
			throw ex;
		}
	}

	@Override
	public Vehicle inactivateVehicle(int id) {
		return lifeCycleChanger.inactivateVehicle(id);
	}

	@Override
	public Vehicle getVehicle(int id) {
		return access.get(id, Vehicle.class);
	}

}
