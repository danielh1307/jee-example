package danielh1307.jee.example.enums;

public class LifeCycleTypeConverter extends EnumConverter {

	@Override
	public EnumInterface convertToEntityAttribute(Integer arg0) {
		return LifeCycleType.forCode(arg0);
	}

}
