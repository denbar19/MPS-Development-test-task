package com.denisenko.mps.vehicle.plane;

import com.denisenko.mps.calculator.Calculator;
import com.denisenko.mps.calculator.PlaneCalculator;
import com.denisenko.mps.characteristics.AirPlaneCharacteristics;
import com.denisenko.mps.flight.Flight;
import com.denisenko.mps.persistence.dao.mongo.Repository;
import com.denisenko.mps.point.TemporaryPoint;
import com.denisenko.mps.vehicle.AirVehicle;
import lombok.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AirplaneImpl extends AirVehicle {

    public static final Logger LOG = LoggerFactory.getLogger(AirplaneImpl.class);

    private final Calculator planeCalculation;
    private final Repository repository;

    @Builder
    public AirplaneImpl(TemporaryPoint startPoint, PlaneCalculator planeCalculation,
                        Repository repository, List<Flight> flights,
                        long id, AirPlaneCharacteristics characteristics) {
        super(id, characteristics, flights);
        super.setStartPoint(startPoint);
        this.planeCalculation = planeCalculation;
        this.repository = repository;
        super.position = startPoint;
        this.repository.save(startPoint);
        startFlight();
    }

    @Scheduled(fixedRateString = "${threshold}")
    public void makeAdjustmentContinueFlight() {
        LOG.info("Scheduler invocation");

        //TemporaryPoint temporaryPoint = generator.generateTelemetry();
//        List<TemporaryPoint> temporaryPoints =
//                planeCalculation.calculateRoute(super.getCharacteristics(),
//                        super.getFlights().iterator().next().getWayPoints());

        planeCalculation.calculateAdjustment();
    }

    @Scheduled(fixedRateString = "${save.position.threshold}")
    protected void scheduleSave() {
        repository.save(super.getPosition());
    }

    public TemporaryPoint getStartPoint() {
        return super.getStartPoint();
    }

    private void startFlight() {
        LOG.info("previous flights info airplane = {}", super.getId());
        repository.findAllById(new LinkedList<>(Arrays.asList("1")));
    }
}
