package com.weather.client.outfit.suggestion;

import com.weather.client.outfit.exception.OutfitLevelNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static com.weather.client.outfit.suggestion.OutfitLevel.*;
import static com.weather.client.outfit.suggestion.OutfitLevelPredicates.*;

@Component
public class SuggestionComponent {

    private List<OutfitLevelMatcher> outfitLevelMatcher;

    public SuggestionComponent() {
        this.outfitLevelMatcher = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        addMatcher(new GenericOutfitlevelMatcher(LEVEL_1, isLevelOneOutfit()));
        addMatcher(new GenericOutfitlevelMatcher(LEVEL_2, isLevelTwoOutfit()));
        addMatcher(new GenericOutfitlevelMatcher(LEVEL_3, isLevelThreeOutfit()));
        addMatcher(new GenericOutfitlevelMatcher(LEVEL_4, isLevelFourOutfit()));
        addMatcher(new GenericOutfitlevelMatcher(LEVEL_5, isLevelFiveOutfit()));
    }

    public OutfitLevel calculate(double temperature) {
        return outfitLevelMatcher.stream()
                .filter(matcher -> matcher.match(temperature))
                .map(OutfitLevelMatcher::getLevel)
                .findFirst()
                .orElseThrow(() -> new OutfitLevelNotFoundException("Outfit-Level for temperature: \""
                        + temperature + "\" not found."));
    }

    private void addMatcher(OutfitLevelMatcher matcher) {
        outfitLevelMatcher.add(matcher);
    }

}
