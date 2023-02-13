package com.denysenko.mps.model.point;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=true)
@JsonDeserialize(builder = TemporaryPoint.TemporaryPointBuilder.class)
public class TemporaryPoint extends Point {

    @Builder
    public TemporaryPoint(double latitude, double longitude, float altitude, float flightSpeed) {
        super(latitude, longitude, altitude, flightSpeed);
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class TemporaryPointBuilder {

    }
}
