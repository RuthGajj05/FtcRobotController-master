package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
@Autonomous
public class SpinCarousel extends LinearOpMode {
    DcMotor carousel;
    HardwareMap hwMap = null;
    double ticks = 537.7;
    double cWheelCircumference = 11.87;
    double ticksPerInch = ticks/cWheelCircumference;
    double carouselCircumference = 11.45;

    public void init(HardwareMap Map) {
        carousel = hwMap.get(DcMotor.class, "Carousel");
        carousel.setDirection(DcMotor.Direction.FORWARD);
        carousel.setPower(0);
        carousel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        carousel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    private ElapsedTime runtime = new ElapsedTime();
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();
        runtime.reset();
        while (opModeIsActive()) {
            runtime.reset();
            while (runtime.milliseconds() < 20000) {
                telemetry.addData("Status", "Start");
                spinCarousel(carouselCircumference);
                stopCarousel();
                telemetry.addData("Status", "Stopped");
            }
        }

    }

    public void spinCarousel(double distanceInInches) {
        telemetry.addData("Status", "Starting");
        carousel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        telemetry.addData("Status", "Reset");
        double distanceInTicks = distanceInInches * ticksPerInch;
        carousel.setTargetPosition((int) distanceInTicks);
        carousel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        telemetry.addData("Status", "Running to Position");
        carousel.setPower(0.5);
    }

    public void stopCarousel() {
        carousel.setPower(0);
    }
}
