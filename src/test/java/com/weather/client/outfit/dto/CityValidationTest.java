package com.weather.client.outfit.dto;

import org.hibernate.validator.constraints.NotEmpty;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.metadata.ConstraintDescriptor;
import java.util.Iterator;
import java.util.Set;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CityValidationTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterClass
    public static void tearDown() {
        validatorFactory.close();
    }

    @Test
    public void testWeatherSearchValidation() {
        CityDto cityDto = CityDto.builder().build();

        Set<ConstraintViolation<CityDto>> violations = validator.validate(cityDto);

        assertThat(violations.size(), is(2));

        Iterator<ConstraintViolation<CityDto>> iterator = violations.iterator();
        assertAnnotationType(iterator.next().getConstraintDescriptor(), NotEmpty.class);
        assertAnnotationType(iterator.next().getConstraintDescriptor(), NotEmpty.class);
    }

    private void assertAnnotationType(ConstraintDescriptor<?> constraintDescriptor, Class<?> expecteAnntotation) {
        assertThat(constraintDescriptor.getAnnotation().annotationType(), equalTo(expecteAnntotation));
    }

}
