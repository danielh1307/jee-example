package danielh1307.jee.example.enums;

import danielh1307.jee.example.vehicle.BikeType;

public class BikeTypeConverter extends EnumConverter {

	@Override
	public EnumInterface convertToEntityAttribute(Integer arg0) {
		return BikeType.forCode(arg0);
	}

}
