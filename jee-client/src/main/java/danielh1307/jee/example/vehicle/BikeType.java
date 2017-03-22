package danielh1307.jee.example.vehicle;

import danielh1307.jee.example.enums.EnumInterface;

public enum BikeType implements EnumInterface {

	Mountainbike(1),
	Rennrad(2),
	Cityrad(3);
	
	private int code;
	
	private BikeType(int code) {
		this.code = code;
	}
	
	public static BikeType forCode(int code) {
		for (BikeType b : values()) {
			if (b.code == code) {
				return b;
			}
		}
		return null;
	}
	
	@Override
	public int getCode() {
		return code;
	}
}
