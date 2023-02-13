package com.denysenko.mps.mpsdevelopmenttesttask.configuration;

import com.denysenko.mps.animation.Animation;
import com.denysenko.mps.calculator.PlaneCalculator;
import com.denysenko.mps.characteristics.AirPlaneCharacteristics;
import com.denysenko.mps.model.flight.Flight;
import com.denysenko.mps.persistence.dao.mongo.Repository;
import com.denysenko.mps.model.point.Point;
import com.denysenko.mps.model.point.TemporaryPoint;
import com.denysenko.mps.model.point.WayPoint;
import com.denysenko.mps.model.vehicle.AirVehicle;
import com.denysenko.mps.model.vehicle.plane.AirplaneImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@ComponentScan("com.denysenko.mps.calculator")
public class AirplanesConfig {

    private static final Logger LOG = LoggerFactory.getLogger(AirplanesConfig.class);

    private final ApplicationContext appContext;
    private final ObjectMapper mapper = new ObjectMapper();

    public static final String WAY_POINTS_PATH = "classpath:points/wayPoints.json";

    private final TemporaryPoint firstStartPoint = new TemporaryPoint(50.421830, 30.086354, 180, 0);
    private final TemporaryPoint secondStartPoint = new TemporaryPoint(50.422830, 30.096354, 190, 0);
    private final TemporaryPoint thirdStartPoint = new TemporaryPoint(50.423830, 30.106354, 200, 0);

    private final float firstMaxSpeed = 10;
    private final float firstAcceleration = 8;
    private final float firstAscension = 5;
    private final float firstAngularSpeed = 20;

    private final float secondMaxSpeed = 10;
    private final float secondAcceleration = 8;
    private final float secondAscension = 5;
    private final float secondAngularSpeed = 20;

    private final float thirdMaxSpeed = 10;
    private final float thirdAcceleration = 8;
    private final float thirdAscension = 5;
    private final float thirdAngularSpeed = 20;

    private final List<AirVehicle> planes = new ArrayList<>();



    @SneakyThrows
    @Bean
    public AirVehicle firstPlane() {
        AirplaneImpl firstPlane = AirplaneImpl.builder()
                                              .id(1)
                                              .startPoint(firstStartPoint)
                                              .planeCalculation(appContext.getBean(PlaneCalculator.class))
                                              .repository(repository())
                                              .characteristics(first())
                                              .flights(getFlights())
                                              .build();
        planes.add(firstPlane);
        return firstPlane;
    }
    /*
    @SneakyThrows
    @Bean
    public AirVehicle secondPlane() {
        AirplaneImpl secondPlane = AirplaneImpl.builder()
                                               .id(2)
                                               .startPoint(secondStartPoint)
                                               .planeCalculation(appContext.getBean(PlaneCalculator.class))
                                               .repository(repository())
                                               .characteristics(second())
                                               .flights(getFlights())
                                               .build();
        planes.add(secondPlane);
        return secondPlane;
    }

    @SneakyThrows
    @Bean
    public AirVehicle thirdPlane() {
        AirplaneImpl thirdPlane = AirplaneImpl.builder()
                                              .id(3)
                                              .startPoint(thirdStartPoint)
                                              .planeCalculation(appContext.getBean(PlaneCalculator.class))
                                              .repository(repository())
                                              .characteristics(third())
                                              .flights(getFlights())
                                              .build();
        planes.add(thirdPlane);
        return thirdPlane;
    }*/

    @Bean
    public Repository repository() {
        return new Repository();
    }

    @Bean
    public AirPlaneCharacteristics first() {
        return AirPlaneCharacteristics.builder()
                                      .maxSpeed(firstMaxSpeed)
                                      .acceleration(firstAcceleration)
                                      .ascension(firstAscension)
                                      .angularSpeed(firstAngularSpeed)
                                      .build();
    }

    @Bean
    public AirPlaneCharacteristics second() {
        return AirPlaneCharacteristics.builder()
                                      .maxSpeed(secondMaxSpeed)
                                      .acceleration(secondAcceleration)
                                      .ascension(secondAscension)
                                      .angularSpeed(secondAngularSpeed)
                                      .build();
    }

    @Bean
    public AirPlaneCharacteristics third() {
        return AirPlaneCharacteristics.builder()
                                      .maxSpeed(thirdMaxSpeed)
                                      .acceleration(thirdAcceleration)
                                      .ascension(thirdAscension)
                                      .angularSpeed(thirdAngularSpeed)
                                      .build();
    }

    @Bean
    public Animation animation() {
        Animation animation = Animation.builder()
                                       .title("Three planes flight animation")
                                       .vehicle(planes.get(0))
                                       //.vehicle(planes.get(1))
                                       //.vehicle(planes.get(2))
                                       .build();
        animation.createFrameAndPrepare("Three planes flight animation");
        animation.repaint();
        planes.forEach(Component::repaint);
        return animation;
    }

    private List<Flight> getFlights() {
        List<Flight> flights = new LinkedList<>();
        List<WayPoint> wayPoints = getWayPoints();
        LOG.debug("WayPoints: \n{}", Arrays.toString(wayPoints.toArray()));

        Map<WayPoint, List<Point>> passedPoints = new HashMap<>();

        wayPoints.forEach(wp -> passedPoints.put(wp, new LinkedList<>()));

        Flight flight = Flight.builder()
                              .number(1)
                              .wayPoints(wayPoints)
                              .passedPoints(passedPoints)
                              .build();
        flight.setCurrentWayPoint(wayPoints.get(0));
        flights.add(flight);
        return flights;
    }

    private List<WayPoint> getWayPoints() {
        List<WayPoint> wayPoints = null;
        try {
            wayPoints = Optional.ofNullable(mapper.readValue(ResourceUtils.getFile(WAY_POINTS_PATH),
                                                             new TypeReference<LinkedList<WayPoint>>() {}))
                                .orElse(new LinkedList<>());
        } catch (IOException e) {
            LOG.error("Error reading the file from path={}", WAY_POINTS_PATH, e);
        }
        return wayPoints;
    }

}
