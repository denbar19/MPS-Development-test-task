package com.denysenko.mps.generator;

import com.denysenko.mps.model.point.Point;
import org.springframework.beans.factory.annotation.Value;

//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
//@Component
public class TelemetryGeneratorFactory {

    @Value("${deviation.percent}")
    private int deviationPercent;

    public TelemetryGenerator getTelemetryGenerator(Point basePoint) {
//        return new TelemetryAutopilotImpl(deviationPercent, basePoint);
        return null;
    }
}
