package com.denysenko.mps.characteristics;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
//@Builder
public class Characteristics {
    private final float maxSpeed; // m/s
    private final float acceleration; // m/s^2
    private final float ascension; // m/s
    private final float angularSpeed; // deg/s
}
