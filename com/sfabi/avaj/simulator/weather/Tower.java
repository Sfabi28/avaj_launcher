package com.sfabi.avaj.simulator.weather;

import java.util.ArrayList;
import java.util.List;

import com.sfabi.avaj.simulator.logger.SimulationLogger;
import com.sfabi.avaj.simulator.vehicles.Flyable;

public class Tower {
	private List<Flyable> observers = new ArrayList<>();

	public void register(Flyable p_flyable) {
		if (!observers.contains(p_flyable)) {
			observers.add(p_flyable);
			SimulationLogger.log("Tower says: " + p_flyable.getIdentifier() + " registered to weather tower.");
		}
	}

	public void unregister(Flyable p_flyable) {
		SimulationLogger.log("Tower says: " + p_flyable.getIdentifier() + " unregistered from weather tower.");
		observers.remove(p_flyable);
	}

	protected void conditionChanged() {
		List<Flyable> observersSnapshot = new ArrayList<>(observers);
		for (Flyable flyable : observersSnapshot) {
			flyable.updateConditions();
		}
	}
}
