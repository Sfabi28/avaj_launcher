package com.sfabi.avaj.simulator.vehicles;

import com.sfabi.avaj.simulator.weather.Coordinates;

public class Balloon extends Aircraft{
	public Balloon(long p_id, String p_name, Coordinates p_coordinates) {
		super(p_id, p_name, p_coordinates);
	}

	@Override
	public void updateConditions() {
		// TODO
	}
}
