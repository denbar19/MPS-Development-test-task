package com.denysenko.mps.math;

import com.denysenko.mps.generator.TelemetryGenerator;
import org.springframework.stereotype.Component;

@Component
public class ComputerFactory {
    public Computer getComputer(TelemetryGenerator generator) {
        return new Computer(generator);
    }
}
