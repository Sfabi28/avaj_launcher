package com.sfabi.avaj.simulator.vehicles;

import com.sfabi.avaj.simulator.weather.WeatherTower;

public abstract class Flyable {
	protected WeatherTower weatherTower;

	public abstract void updateConditions();

	public void registerTower(WeatherTower p_tower) {
		// TODO
	}
}
