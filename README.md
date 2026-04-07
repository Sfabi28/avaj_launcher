# ✈️ Avaj Launcher

A minimal aircraft weather simulation written in Java, based on UML design and classic OOP patterns (Factory, Singleton, Observer).

## 📌 Overview

Avaj Launcher reads a scenario file, creates aircraft, registers them to a weather tower, and runs a weather simulation for a fixed number of cycles.

During the simulation, each aircraft:
- reacts to the current weather,
- updates its coordinates,
- logs a custom message,
- lands and unregisters when its height reaches `0`.

The simulation output is written to `simulation.txt`.

## 🧱 Architecture

This project follows the provided UML and design patterns:

- **Factory**: `AircraftFactory` creates `Balloon`, `JetPlane`, `Helicopter` with unique IDs.
- **Singleton**:
  - `AircraftFactory`
  - `WeatherProvider`
- **Observer**:
  - `Tower` manages registered `Flyable` observers
  - `WeatherTower` notifies all aircraft on each weather change

Main components:
- `Simulator`: entry point, parsing + simulation loop
- `WeatherProvider`: weather generation from coordinates
- `WeatherTower`: weather access + notification trigger
- `Flyable` / `Aircraft` / concrete aircraft classes
- `SimulationLogger`: shared logger used by all classes

## 🗂️ Scenario Format

The scenario file format is:

1. First line: positive integer = number of simulation cycles
2. Next lines: aircraft definitions

```text
TYPE NAME LONGITUDE LATITUDE HEIGHT
```

Example:

```text
25
Balloon B1 2 3 20
JetPlane J1 23 44 32
Helicopter H1 654 33 20
```

## ▶️ Build & Run

From the project root:

```bash
find * -name "*.java" > sources.txt
javac @sources.txt
java com.sfabi.avaj.simulator.Simulator com/sfabi/avaj/simulator/scenaries/scenario1.txt
```

Then inspect output:

```bash
cat simulation.txt
```

## ✅ Current Features

- ✅ Scenario parsing and validation
- ✅ Unique aircraft ID assignment
- ✅ Weather-based coordinate updates
- ✅ Height clamping to `100`
- ✅ Landing and automatic unregistration
- ✅ Centralized logging to `simulation.txt`
- ✅ Registration/unregistration log messages
- ✅ Custom per-aircraft weather messages

## ⚠️ Validation Behavior

If input is invalid, execution stops and an error is printed.

Examples of invalid input:
- wrong number of arguments
- invalid first line (not a positive integer)
- malformed aircraft line
- invalid aircraft type
- invalid coordinates format
- negative coordinates/height

## 🧪 Useful Test Scenarios

You can test with files under:

- `com/sfabi/avaj/simulator/scenaries/scenario1.txt`
- `com/sfabi/avaj/simulator/scenaries/missing_informations.txt`

## 🙌 Notes

- `.class` files and `simulation.txt` are ignored by Git (`.gitignore`).
- No external libraries are required.
