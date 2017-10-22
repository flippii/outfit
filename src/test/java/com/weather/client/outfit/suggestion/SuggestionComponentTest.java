package com.weather.client.outfit.suggestion;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static com.weather.client.outfit.suggestion.OutfitLevel.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class SuggestionComponentTest {

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {  5.0, LEVEL_5},
                {  6.0, LEVEL_4 },
                { 15.0, LEVEL_4 },
                { 16.0, LEVEL_3 },
                { 21.0, LEVEL_3 },
                { 22.0, LEVEL_2 },
                { 26.0, LEVEL_2 },
                { 27.0, LEVEL_1 }
        });
    }

    @Parameter public double actualTemperature;
    @Parameter(1) public OutfitLevel expectedOutfitLevel;

    private SuggestionComponent suggestionComponent;

    @Before
    public void setUp() {
        suggestionComponent = new SuggestionComponent();
        suggestionComponent.init();
    }

    @Test
    public void testOutfitLevelCalculation() {
        OutfitLevel actualOutfitLevel = suggestionComponent.calculate(actualTemperature);

        assertThat(actualOutfitLevel)
                .isNotNull()
                .isEqualTo(expectedOutfitLevel);
    }

}
