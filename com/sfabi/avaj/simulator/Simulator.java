package com.sfabi.avaj.simulator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sfabi.avaj.simulator.exceptions.InvalidLineException;
import com.sfabi.avaj.simulator.logger.SimulationLogger;
import com.sfabi.avaj.simulator.vehicles.AircraftFactory;
import com.sfabi.avaj.simulator.vehicles.Flyable;
import com.sfabi.avaj.simulator.weather.Coordinates;
import com.sfabi.avaj.simulator.weather.WeatherTower;

public class Simulator {

	private static int simulations;

	private static Flyable parseLine(String line, AircraftFactory factory) throws InvalidLineException {
		String[] parts = line.trim().split("\\s+");

		if (parts.length != 5) {
			throw new InvalidLineException("Invalid number of parameters in line");
		}

		String type = parts[0];
		String name = parts[1];
		
		if (!type.equals("Balloon") && !type.equals("JetPlane") && !type.equals("Helicopter")) {
    		throw new InvalidLineException("Invalid type");
		}

		int longitude;
		int latitude;
		int height;
		try {
			longitude = Integer.parseInt(parts[2]);
			latitude = Integer.parseInt(parts[3]);
			height = Integer.parseInt(parts[4]);
		} catch (NumberFormatException e) {
			throw new InvalidLineException("Coordinates must be integers");
		}

		if (longitude < 0 || latitude < 0 || height < 0) {
			throw new InvalidLineException("Coordinates out of range");
		}

		Coordinates coordinates = new Coordinates(longitude, latitude, height);
		Flyable flyable = factory.newAircraft(type, name, coordinates);
		if (flyable == null) {
			throw new InvalidLineException("Invalid type");
		}

		return flyable;
	}

	public static void main(String[] args) {

		int	numArgs = args.length;
		if (numArgs != 1) {
			System.out.println("Error: 1 argument expected");
			return;
		}

		int lineNumber = -1;
		List<Flyable> flyables = new ArrayList<>();
		AircraftFactory factory = AircraftFactory.getFactory();

		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String line;
            while ((line = br.readLine()) != null) {
				lineNumber++;
				if (lineNumber == 0) {
					try {
						simulations = Integer.parseInt(line.trim());
						if (simulations <= 0) {
                    		System.out.println("Error: first line must be a positive integer");
                    	return;
                		}
					} catch (NumberFormatException e) {
						System.out.println("Error: first line must be an integer");
						return;
					}
					continue;
				}

				try {
					Flyable flyable = parseLine(line, factory);
					flyables.add(flyable);
				} catch (InvalidLineException e) {
					System.out.println("Error: flyable must follow the rule: Type + Name + Longitude + Latitude + Height");
						return;
				}
            }

			if (lineNumber == -1) {
				System.out.println("Error: empty scenario file");
				return;
			}

			WeatherTower tower = new WeatherTower();
			SimulationLogger.init("simulation.txt");
			for (Flyable flyable : flyables) {
				flyable.registerTower(tower);
			}

			int i = 0;
			while (i < simulations) {
				tower.changeWeather();
				i++;
			}


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
			SimulationLogger.close();
		}
	}
}
