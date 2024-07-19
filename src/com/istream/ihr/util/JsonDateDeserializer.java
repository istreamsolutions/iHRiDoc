package com.istream.ihr.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class JsonDateDeserializer extends JsonDeserializer<Date> {
	@Override
	public Date deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		
		Date date = null;
		
		if(jp != null && jp.getText() != null && !jp.getText().isEmpty()) {
			String dateTxt = jp.getText();
	
			try {
				date = format.parse(dateTxt);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}
		
		return date;
	}
}