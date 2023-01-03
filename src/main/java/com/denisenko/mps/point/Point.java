package com.denisenko.mps.point;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Point {

    private final double latitude;
    private final double longitude;
    private final float altitude;
    private final float flightSpeed;

}
