package com.weather.client.outfit.suggestion;

import java.util.function.Predicate;

class OutfitLevelPredicates {

    static Predicate<Double> isLevelOneOutfit() {
        return (temperature) -> temperature > 26.0;
    }

    static Predicate<Double> isLevelTwoOutfit() {
        return (temperature) -> temperature > 21.0 && temperature <= 26.0;
    }

    static Predicate<Double> isLevelThreeOutfit() {
        return (temperature) -> temperature > 15.0 && temperature <= 21.0;
    }

    static Predicate<Double> isLevelFourOutfit() {
        return (temperature) -> temperature > 5.0 && temperature <= 15.0;
    }

    static Predicate<Double> isLevelFiveOutfit() {
        return (temperature) -> temperature <= 5.0;
    }

}
