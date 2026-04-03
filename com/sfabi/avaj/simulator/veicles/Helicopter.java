package com.sfabi.avaj.simulator.veicles;

import com.sfabi.avaj.simulator.weather.Coordinates;

public class Helicopter extends Aircraft{
	public Helicopter(long p_id, String p_name, Coordinates p_coordinates) {
		super(p_id, p_name, p_coordinates);
	}

	@Override
	public void updateConditions() {
		// TODO
	}
}
