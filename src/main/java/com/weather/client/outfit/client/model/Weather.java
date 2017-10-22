package com.weather.client.outfit.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class Weather {

    private int id;
    private String name;
    private Instant timestamp;

    private Actual actual;
    private List<Info> info;
    private Geo geo;
    private Wind wind;
    private Internal internal;

    @JsonSetter("dt")
    public void setTimestamp(long unixTime) {
        this.timestamp = Instant.ofEpochMilli(unixTime * 1000);
    }

    @JsonSetter(value = "main")
    public void setActual(Actual actual) {
        this.actual = actual;
    }

    @JsonSetter(value = "weather")
    public void setInfo(List<Info> info) {
        this.info = info;
    }

    @JsonSetter(value = "coord")
    public void setGeo(Geo geo) {
        this.geo = geo;
    }

    @JsonSetter(value = "sys")
    public void setInternal(Internal internal) {
        this.internal = internal;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown=true)
    public static class Actual {

        private double temp;
        private int pressure;
        private int humidity;
        private double tempMin;
        private double tempMax;
        private String icon;

        @JsonSetter(value = "temp_min")
        public void setTempMin(double tempMin) {
            this.tempMin = tempMin;
        }

        @JsonSetter(value = "temp_max")
        public void setTempMax(double tempMax) {
            this.tempMax = tempMax;
        }

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Info {

        private int id;
        private String main;
        private String description;
        private String icon;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Geo {

        private double lat;
        private double lon;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Wind {

        private double speed;
        private double deg;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown=true)
    public static class Internal {

        private int id;
        private String message;
        private String country;

    }

}
