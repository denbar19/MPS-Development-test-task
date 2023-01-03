package com.denisenko.mps.characteristics;


import lombok.Builder;

public class AirPlaneCharacteristics extends Characteristics {

    @Builder
    public AirPlaneCharacteristics(float maxSpeed, float acceleration, float ascension, float angularSpeed) {
        super(maxSpeed, acceleration, ascension, angularSpeed);
    }
}
