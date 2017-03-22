package danielh1307.jee.example.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class LocalDateConverter implements AttributeConverter<LocalDate, String> {
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy"); 

	@Override
	public String convertToDatabaseColumn(LocalDate attribute) {
		return formatter.format(attribute);
	}

	@Override
	public LocalDate convertToEntityAttribute(String dbData) {
		return LocalDate.parse(dbData, formatter);
	}
	
	

}
