package com.denysenko.mps.flightmanager;

import com.denysenko.mps.model.flight.Flight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class FlightsManager {

    public static final Logger LOG = LoggerFactory.getLogger(FlightsManager.class);

    private final Set<Flight> flights = new HashSet<>();

    public Set<Flight> addFlight(Flight flight) {
        flights.add(flight);
        return flights;
    }

    public Set<Flight> removeFlight(Flight flight) {
        flights.remove(flight);
        return flights;
    }

    @PostConstruct
    public void startFLight() {
        LOG.info("Flight started");
    }
}
