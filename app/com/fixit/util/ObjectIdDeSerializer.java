package com.fixit.util;

import java.io.IOException;

import org.bson.types.ObjectId;

import play.Logger;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.mongodb.DBRef;

public class ObjectIdDeSerializer extends JsonDeserializer<Object> {

	@Override
	public Object deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		
		Logger.debug("ObjectIdDeSerializer.deserialize(...)");
		
		Object object = jp.getEmbeddedObject();
		if (object == null) {
			return null;
		} else if (object instanceof ObjectId) {
			return object.toString();
		} else if (object instanceof DBRef) {
			Object id = ((DBRef) object).getId();
			if (!(id instanceof ObjectId)) {
				throw ctxt.instantiationException(String.class,
						"Expected an ObjectId id in the DBRef to deserialise to string, but found "
								+ id.getClass());
			}
			return id.toString();
		} else {
			throw ctxt.instantiationException(String.class,
					"Expected an ObjectId to deserialise to string, but found "
							+ object.getClass());
		}
	}
}
