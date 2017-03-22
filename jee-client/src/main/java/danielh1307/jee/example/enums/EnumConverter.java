package danielh1307.jee.example.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public abstract class EnumConverter implements AttributeConverter<EnumInterface, Integer> {

	@Override
	public Integer convertToDatabaseColumn(EnumInterface arg0) {
		return arg0.getCode();
	}

	@Override
	public abstract EnumInterface convertToEntityAttribute(Integer arg0);
}
