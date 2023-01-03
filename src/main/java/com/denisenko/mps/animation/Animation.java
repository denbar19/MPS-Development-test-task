package com.denisenko.mps.animation;

import com.denisenko.mps.vehicle.AirVehicle;

import javax.swing.*;
import java.awt.*;

public class Animation extends JPanel {

    private JFrame frame;

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
        setPreferredSize(new Dimension(600, 600));

    }

    private void setProportions(int numberColumns) {
        final int frameWidth = this.getPreferredSize().width;
        final int frameHeight = this.getPreferredSize().height;

        /*int spacesSumWidth = frameWidth * spacesInPercent / 100;
        int columnsSumWidth = frameWidth - spacesSumWidth;

        columnWidth = Math.max(columnsSumWidth / numberColumns, 1);
        columnSpacer = Math.max(spacesSumWidth / (numberColumns - 1), 1);

        columnHeightMultiplier = frameHeight / numberColumns;*/
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
