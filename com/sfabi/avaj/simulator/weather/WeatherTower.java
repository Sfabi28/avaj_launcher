package com.sfabi.avaj.simulator.weather;

public class WeatherTower extends Tower {
    public Weather getWeather(Coordinates p_coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(p_coordinates);
    }

    public void changeWeather() {
        conditionChanged();
    }
}