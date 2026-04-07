package com.sfabi.avaj.simulator.vehicles;

import com.sfabi.avaj.simulator.weather.Weather;
import com.sfabi.avaj.simulator.weather.WeatherTower;

public abstract class Flyable {
	protected WeatherTower weatherTower;

	public abstract void updateConditions();

	public void registerTower(WeatherTower p_tower) {
		this.weatherTower = p_tower;
		this.weatherTower.register(this);
	}

	public abstract String getString(Weather weather);

	public abstract String getIdentifier();
}
