package com.denisenko.mps.point;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper=true)
@JsonDeserialize(builder = WayPoint.WayPointBuilder.class)
@Getter
public class WayPoint extends Point {

    private final int id;

    @Builder
    public WayPoint(int id, double latitude, double longitude, float altitude, float flightSpeed) {
        super(latitude, longitude, altitude, flightSpeed);
        this.id = id;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class WayPointBuilder {

    }
}
