package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
@Autonomous(name = "Slides, Intake, Carousel Test")
public class LinearSlidesTest extends LinearOpMode {
    CompAuto auto = new CompAuto();
    public void runOpMode() {
        intake();
        moveSlides();
        carousel();
    }
    public void moveSlides() {
        auto.moveSlides(2);
    }
    public void intake() {
        auto.getIntake(1);
    }
    public void carousel() {
        auto.spinCarousel();
    }
}
