package com.fixit.model;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializationTest {

	
    @Test
    public void simpleCheck() {
    	
    	ObjectMapper mapper = new ObjectMapper();  
    	
    	
        int a = 1 + 1;
        assertThat(a).isEqualTo(2);
    }
}
