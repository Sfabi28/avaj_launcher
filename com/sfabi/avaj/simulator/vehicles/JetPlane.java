package com.sfabi.avaj.simulator.vehicles;

import com.sfabi.avaj.simulator.logger.SimulationLogger;
import com.sfabi.avaj.simulator.weather.Coordinates;
import com.sfabi.avaj.simulator.weather.Weather;

public class JetPlane extends Aircraft{
	public JetPlane(long p_id, String p_name, Coordinates p_coordinates) {
		super(p_id, p_name, p_coordinates);
	}

	public String getString(Weather weather) {
		String res;

		switch (weather) {
			case SUN:
				res = "look at the sunset!";
				break;
			case RAIN:
				res = "look at those dark clouds, we should pay attention to lightnings";
				break;
			case FOG:
				res = "pretty foggy but not a big deal";
				break;
			case SNOW:
				res = "I hope we can land even if it's snowing";
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
				latitude += 10;
				height += 2;
				break;
			case RAIN:
				latitude += 5;
				break;
			case FOG:
				latitude += 1;
				break;
			case SNOW:
				height -= 7;
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
