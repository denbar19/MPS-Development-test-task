package com.denysenko.mps.mpsdevelopmenttesttask.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalculationConfig {

    @Value("${threshold}")
    private float threshold;

    @Value("${way.point.pass.precision}")
    private float precision;

    @Value("${deviation.percent}")
    private Integer deviationPercent;
}
