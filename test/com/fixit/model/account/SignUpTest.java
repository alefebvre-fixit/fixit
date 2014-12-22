package com.fixit.model.account;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.BeforeClass;
import org.junit.Test;

public class SignUpTest {

	private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@BeforeClass
	public static void setUp() {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	@Test
	public void manufacturerIsNull() {
		SignUp signup = new SignUp();

		Set<ConstraintViolation<SignUp>> constraintViolations = validator
				.validate(signup);

		assertEquals(1, constraintViolations.size());
		assertEquals("may not be null", constraintViolations.iterator().next()
				.getMessage());
	}

}
