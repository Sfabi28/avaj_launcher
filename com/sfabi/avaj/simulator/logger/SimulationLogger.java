package com.sfabi.avaj.simulator.logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public final class SimulationLogger {
    private static PrintWriter writer;

    private SimulationLogger() {}

    public static void init(String filePath) throws IOException {
        writer = new PrintWriter(new FileWriter(filePath, false));
    }

    public static void log(String message) {
        if (writer != null) {
            writer.println(message);
        }
    }

    public static void close() {
        if (writer != null) {
            writer.close();
            writer = null;
        }
    }
}