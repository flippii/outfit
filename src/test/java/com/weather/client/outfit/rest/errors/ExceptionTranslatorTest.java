package com.weather.client.outfit.rest.errors;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static com.weather.client.outfit.rest.errors.ExceptionTranslator.SimpleError;

public class ExceptionTranslatorTest {

    @Test
    public void testProcessException() {
        RuntimeException expectedException = new RuntimeException("Dummy Text");

        ExceptionTranslator exceptionTranslator = new ExceptionTranslator();
        SimpleError simpleError = exceptionTranslator.processException(expectedException);

        assertThat(simpleError, notNullValue());
        assertThat(simpleError.getException(), is(expectedException.getClass().getName()));
        assertThat(simpleError.getMessage(), is(expectedException.getMessage()));
    }

}
