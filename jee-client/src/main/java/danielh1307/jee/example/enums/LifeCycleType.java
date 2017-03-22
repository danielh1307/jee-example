package danielh1307.jee.example.enums;

public enum LifeCycleType implements EnumInterface {
	
	AKTIV(1),
	INAKTIV(0);
	
	private int code;
	
	private LifeCycleType(int code) {
		this.code = code;
	}
	
	public static LifeCycleType forCode(int code) {
		for (LifeCycleType l : values()) {
			if (l.code == code) {
				return l;
			}
		}
		return null;
	}
	
	@Override
	public int getCode() {
		return code;
	}

}
