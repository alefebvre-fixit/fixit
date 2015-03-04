package com.fixit.util;

import java.io.IOException;

import play.Logger;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ObjectIdSerializer extends JsonSerializer<Object> {

	@Override
	public void serialize(Object value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonGenerationException {
		
		Logger.debug("ObjectIdSerializer.deserialize(...)");
		
		jgen.writeString(value.toString());
	}

}
