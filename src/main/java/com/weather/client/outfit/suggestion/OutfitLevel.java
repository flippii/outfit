package com.weather.client.outfit.suggestion;

public enum OutfitLevel {

    LEVEL_1(1),
    LEVEL_2(2),
    LEVEL_3(3),
    LEVEL_4(4),
    LEVEL_5(5);

    private int level;

    OutfitLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

}