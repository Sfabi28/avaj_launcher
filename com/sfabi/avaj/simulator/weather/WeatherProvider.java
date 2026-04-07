package com.sfabi.avaj.simulator.weather;

public class WeatherProvider {
    private static final WeatherProvider INSTANCE = new WeatherProvider();
    private static final Weather[] WEATHER = {
        Weather.RAIN,
        Weather.FOG,
        Weather.SUN,
        Weather.SNOW
    };

    private WeatherProvider() {}

    public static WeatherProvider getProvider() {
        return INSTANCE;
    }

    public Weather getCurrentWeather(Coordinates p_coordinates) {
        int index = Math.abs((p_coordinates.getLongitude() * p_coordinates.getLatitude() + p_coordinates.getHeight()) % 4);
        return WEATHER[index];
    }
}
