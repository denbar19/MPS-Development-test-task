package com.denisenko.mps.flight;

import com.denisenko.mps.calculator.PlaneCalculator;
import com.denisenko.mps.characteristics.AirPlaneCharacteristics;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

public class FlightsTest {

    private PlaneCalculator planeCalculation;
    private List<AirPlaneCharacteristics> characteristicsList;

    @BeforeEach
    public void setup() {
        planeCalculation = new PlaneCalculator();
    }

    /*@Test
    public void flightTest_threePlanes() {
        characteristicsList.add()
        Vehicle airPlain1 = AirplaneImpl.builder()
                                        .id(1)
                                        .characteristics(getCharacteristics())
                                        .position()
                                        .flights()
                                        .build();

        planeCalculation.calculateRoute();
    }*/

    private AirPlaneCharacteristics getCharacteristics() {
        return AirPlaneCharacteristics.builder().maxSpeed(10)
                                      .acceleration(8)
                                      .ascension(5)
                                      .angularSpeed(200)
                                      .build();
    }

}
