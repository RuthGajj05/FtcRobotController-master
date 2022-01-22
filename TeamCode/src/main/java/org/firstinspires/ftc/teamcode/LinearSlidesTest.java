package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "IntakeCarouselSlides Test")
public class LinearSlidesTest extends LinearOpMode {
    DcMotor carousel = null;
    DcMotor intake = null;
    DcMotor linearSlides = null;
    HardwareMap hwMap = null;
    public void init(HardwareMap Map) {
        carousel = hwMap.get(DcMotor.class, "Carousel");
        intake = hwMap.get(DcMotor.class, "Intake");
        linearSlides = hwMap.get(DcMotor.class, "Linear Slides");

        carousel.setDirection(DcMotor.Direction.FORWARD);
        intake.setDirection(DcMotor.Direction.REVERSE);
        linearSlides.setDirection(DcMotor.Direction.REVERSE);

        carousel.setPower(0);
        intake.setPower(0);
        linearSlides.setPower(0);

        carousel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        linearSlides.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        carousel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linearSlides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
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
