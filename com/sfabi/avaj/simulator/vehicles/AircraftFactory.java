package com.sfabi.avaj.simulator.vehicles;

import com.sfabi.avaj.simulator.weather.Coordinates;

public class AircraftFactory {
    private static final AircraftFactory INSTANCE = new AircraftFactory();
    private long nextId = 0;

    private AircraftFactory() {}

    public static AircraftFactory getFactory() {
        return INSTANCE;
    }

    public Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) {
        switch (p_type) {
            case "Balloon":
                return new Balloon(++nextId, p_name, p_coordinates);
            case "Helicopter":
                return new Helicopter(++nextId, p_name, p_coordinates);
            case "JetPlane":
                return new JetPlane(++nextId, p_name, p_coordinates);
            default:
                return null;
        }
    }
}