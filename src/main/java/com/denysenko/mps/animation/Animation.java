package com.denysenko.mps.animation;

import com.denysenko.mps.model.vehicle.AirVehicle;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import javax.swing.*;
import java.awt.*;

@RequiredArgsConstructor
@Getter
@Setter
public class Animation extends JPanel {

    private JFrame frame;

    @Value("${frame.width}")
    private int width;

    @Value("${frame.height}")
    private int height;

    Animation(JFrame frame) {
        this.frame = frame;
    }

    public static AnimationBuilder builder() {
        return new AnimationBuilder();
    }

    public void createFrameAndPrepare(String title) {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(width, height));

    }

    public static class AnimationBuilder {
        private JFrame frame;

        AnimationBuilder() {
        }

        public AnimationBuilder title(String title) {
            this.frame = new JFrame(title);
            return this;
        }

        public AnimationBuilder frame(JFrame frame) {
            this.frame = frame;
            return this;
        }

        public AnimationBuilder vehicle(AirVehicle vehicle){
            frame.add((Component) vehicle);
            return this;
        }

        public Animation build() {
            return new Animation(frame);
        }

        public String toString() {
            return "Animation.AnimationBuilder(frame=" + this.frame + ")";
        }
    }
}
