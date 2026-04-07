package com.sfabi.avaj.simulator.vehicles;

import com.sfabi.avaj.simulator.logger.SimulationLogger;
import com.sfabi.avaj.simulator.weather.Coordinates;
import com.sfabi.avaj.simulator.weather.Weather;

public class Balloon extends Aircraft{
	public Balloon(long p_id, String p_name, Coordinates p_coordinates) {
		super(p_id, p_name, p_coordinates);
	}

	public String getString(Weather weather) {
		String res;

		switch (weather) {
			case SUN:
				res = "wow such a nice day";
				break;
			case RAIN:
				res = "my balloon is wet now";
				break;
			case FOG:
				res = "is that a bird? I can't see";
				break;
			case SNOW:
				res = "why am I here while it's snowing?";
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
				longitude += 2;
				height += 4;
				break;
			case RAIN:
				height -= 5;
				break;
			case FOG:
				height -= 3;
				break;
			case SNOW:
				height -= 15;
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
