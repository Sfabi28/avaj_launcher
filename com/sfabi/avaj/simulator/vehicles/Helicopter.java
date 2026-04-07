package com.sfabi.avaj.simulator.vehicles;

import com.sfabi.avaj.simulator.logger.SimulationLogger;
import com.sfabi.avaj.simulator.weather.Coordinates;
import com.sfabi.avaj.simulator.weather.Weather;

public class Helicopter extends Aircraft{
	public Helicopter(long p_id, String p_name, Coordinates p_coordinates) {
		super(p_id, p_name, p_coordinates);
	}
	public String getString(Weather weather) {
		String res;

		switch (weather) {
			case SUN:
				res = "let's enjoy this sunny day";
				break;
			case RAIN:
				res = "I can't hear anything with this rain";
				break;
			case FOG:
				res = "We should be carefull with this fog";
				break;
			case SNOW:
				res = "Isn't it dangerous to fly with all of this snow?";
				break;
			default:
				res = "idk how I got here";
				break;
		}
		return res;
	}
	@Override
	public void updateConditions() {
		Weather currentWeather = this.weatherTower.getWeather(this.coordinates);
		SimulationLogger.log(getIdentifier() + ": " + getString(currentWeather));
		int longitude = this.coordinates.getLongitude();
		int latitude = this.coordinates.getLatitude();
		int height = this.coordinates.getHeight();

		switch (currentWeather) {
			case SUN:
				longitude += 10;
				height += 2;
				break;
			case RAIN:
				longitude += 5;
				break;
			case FOG:
				longitude += 1;
				break;
			case SNOW:
				height -= 12;
				break;
			default:
				break;
		}

		if (height > 100) {
			height = 100;
		}
		if (height <= 0) {
			height = 0;
			SimulationLogger.log(getIdentifier() + " landing.");
			this.coordinates = new Coordinates(longitude, latitude, height);
			this.weatherTower.unregister(this);
			return;
		}

		this.coordinates = new Coordinates(longitude, latitude, height);
	}
}
