package com.istream.ihr.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@Component
public class JsonDateTimeSerializer extends JsonSerializer<Date> {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm");

	@Override
	public void serialize(Date date, JsonGenerator gen, SerializerProvider provider) throws IOException, JsonProcessingException {
		
		if(date != null) {
			
			String formattedDate = dateFormat.format(date);
			gen.writeString(formattedDate);
		}
	}
}