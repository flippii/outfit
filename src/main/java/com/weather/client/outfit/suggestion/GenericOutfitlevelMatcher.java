package com.weather.client.outfit.suggestion;

import java.util.function.Predicate;

class GenericOutfitlevelMatcher implements OutfitLevelMatcher {

    private OutfitLevel level;
    private Predicate<Double> predicate;

    GenericOutfitlevelMatcher(OutfitLevel level, Predicate<Double> predicate) {
        this.level = level;
        this.predicate = predicate;
    }

    @Override
    public boolean match(double temperature) {
        return predicate.test(temperature);
    }

    @Override
    public OutfitLevel getLevel() {
        return level;
    }

}
