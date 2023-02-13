package com.denysenko.mps.animation;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@NoArgsConstructor
public class AnimationConfig {

    @Value("${frame.width}")
    private int width;

    @Value("${frame.height}")
    private int height;
}
