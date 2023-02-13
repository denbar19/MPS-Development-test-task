package com.denysenko.mps.model.vehicle.plane;

import com.denysenko.mps.animation.Animation;
import com.denysenko.mps.calculator.Calculator;
import com.denysenko.mps.calculator.PlaneCalculator;
import com.denysenko.mps.characteristics.AirPlaneCharacteristics;
import com.denysenko.mps.model.flight.Flight;
import com.denysenko.mps.model.point.Point;
import com.denysenko.mps.model.vehicle.AirVehicle;
import com.denysenko.mps.persistence.dao.mongo.Repository;
import com.denysenko.mps.model.point.TemporaryPoint;
import com.denysenko.mps.model.point.WayPoint;
import lombok.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AirplaneImpl extends AirVehicle {

    public static final Logger LOG = LoggerFactory.getLogger(AirplaneImpl.class);

    private final Calculator planeCalculation;
    private final Repository repository;

    private Animation animation;

    @Builder
    public AirplaneImpl(TemporaryPoint startPoint, PlaneCalculator planeCalculation,
                        Repository repository, List<Flight> flights,
                        long id, AirPlaneCharacteristics characteristics) {
        super(id, characteristics, flights);
        super.setStartPoint(startPoint);
        planeCalculation.setPlane(this);
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

        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        List<WayPoint> wayPoints = super.getCurrentFlight().getWayPoints();
        for (com.denysenko.mps.model.point.Point point : wayPoints) {
            point.draw(g);
        }
    }

    @Scheduled(fixedRateString = "${save.position.threshold}")
    protected void scheduleSave() {
        repository.save(super.getPosition());
    }

    public Point getStartPoint() {
        return super.getStartPoint();
    }

    private void startFlight() {
        LOG.info("previous flights info airplane = {}", super.getId());
        repository.findAllById(new LinkedList<>(Arrays.asList("1")));
    }
}
