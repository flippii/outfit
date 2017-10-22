package com.weather.client.outfit.suggestion;

interface OutfitLevelMatcher {

    boolean match(double temperature);

    OutfitLevel getLevel();

}
