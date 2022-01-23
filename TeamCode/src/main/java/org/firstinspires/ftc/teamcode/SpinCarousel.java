package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Hardware;

@Autonomous
public class SpinCarousel extends LinearOpMode {
    DcMotor r1 = null;
    DcMotor r2 = null;
    DcMotor l1 = null;
    DcMotor l2 = null;
    DcMotor carousel;
    DcMotor intake;
    DcMotor slides;
    Servo drop = null;
    HardwareMap hwMap;

    double ticks = 537.7;
    double cWheelCircumference = 3 * 3.14;
    double ticksPerInch = ticks/cWheelCircumference;
    double carouselCircumference = 11.45 * 3.14;

    double level1Height;
    double level2Height;
    double level3Height = 19;

    public void init(HardwareMap Map) {
        r1 = hwMap.get(DcMotor.class, "r1");
        r2 = hwMap.get(DcMotor.class, "r2");
        l1 = hwMap.get(DcMotor.class, "l1");
        l2 = hwMap.get(DcMotor.class, "l2");
        drop = hwMap.get(Servo.class, "Drop");
        carousel = hardwareMap.get(DcMotor.class, "Carousel");
        intake = hardwareMap.get(DcMotor.class, "Intake");
        slides = hardwareMap.get(DcMotor.class, "Linear Slides");

        r1.setDirection(DcMotor.Direction.REVERSE);
        r2.setDirection(DcMotor.Direction.REVERSE);
        l1.setDirection(DcMotor.Direction.FORWARD);
        l2.setDirection(DcMotor.Direction.FORWARD);
        drop.setDirection(Servo.Direction.FORWARD);
        carousel.setDirection(DcMotor.Direction.REVERSE);
        intake.setDirection(DcMotor.Direction.FORWARD);
        slides.setDirection(DcMotor.Direction.FORWARD);

        r1.setPower(0);
        r2.setPower(0);
        l1.setPower(0);
        l2.setPower(0);
        drop.setPosition(0);
        carousel.setPower(0);
        intake.setPower(0);
        slides.setPower(0);

        r1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        r2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        l1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        l2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        carousel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slides.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        r1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        r2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        l1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        l2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        carousel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    private ElapsedTime runtime = new ElapsedTime();
    @Override
    public void runOpMode() {
        init(hwMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        telemetry.addData("Status", "Start");
        telemetry.update();

        spinCarousel(carouselCircumference, 2.5);
        spinIntake();
        moveSlides(6, level3Height, 0.2);

        telemetry.addData("Status", "End");
        telemetry.update();

        while (opModeIsActive()) {
            idle();
        }
    }

    public void spinCarousel(double distanceInInches, double timeout) {
        double distanceInTicks = distanceInInches * ticksPerInch;
        carousel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        carousel.setTargetPosition((int) distanceInTicks);
        carousel.setPower(0.1);

        if (opModeIsActive() && runtime.seconds() < timeout && intake.isBusy()) {
            while (opModeIsActive()) {
                telemetry.addData("Intake", "Turning %7d revolution(s)", distanceInInches/carouselCircumference);
                intake.getCurrentPosition();
                telemetry.update();
            }
        }
        carousel.setPower(0);
    }

    public void spinIntake() {
        intake.setPower(1);
        sleep(3000);
        intake.setPower(0);
    }

    public void moveSlides(double timeout, double slidesHeight, double power) {
        /*slides.setPower(0.1);
        sleep(6000);
        slides.setPower(0);
        sleep(1000);
        slides.setPower(-0.1);
        sleep(4500);
        slides.setPower(0);*/

        double distanceInTicks = slidesHeight * ticksPerInch;
        carousel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        carousel.setTargetPosition((int) distanceInTicks);
        carousel.setPower(power);

        slides.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slides.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slides.setTargetPosition((int) distanceInTicks);
        slides.setPower(0.1);
        if (opModeIsActive() && runtime.seconds() < timeout && intake.isBusy()) {
            while (opModeIsActive()) {
                telemetry.addData("Slides", "Moving to Level %7d, %7d height", slidesHeight, distanceInTicks);
                intake.getCurrentPosition();
                telemetry.update();
            }
        }

        slides.setPower(0);

        runtime.reset();
        slides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slides.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slides.setTargetPosition((int) distanceInTicks);
        slides.setPower(-power/2);
        if (opModeIsActive() && runtime.seconds() < timeout && slides.isBusy()) {
            while (opModeIsActive()) {
                telemetry.addData("Slides", "Moving to Level %7d, %7d height", slidesHeight, distanceInTicks);
                intake.getCurrentPosition();
                telemetry.update();
            }
        }

        slides.setPower(0);
    }

    // moves forward
    public void moveForward(double power) {
        r1.setPower(power);
        r2.setPower(-power);
        l1.setPower(-power);
        l2.setPower(power);
    }
    // moves right
    public void moveRight(double power) {
        r1.setPower(power);
        r2.setPower(-power);
        l1.setPower(power);
        l2.setPower(-power);
    }
    // move lefts
    public void moveLeft(double power) {
        r1.setPower(-power);
        r2.setPower(power);
        l1.setPower(-power);
        l2.setPower(power);
    }
    // moves backward
    public void moveBackward(double power) {
        r1.setPower(power);
        r2.setPower(power);
        l1.setPower(-power);
        l2.setPower(-power);
    }
    // spins rights
    public void spinRight(double power, double distance) {
        r1.setPower(power);
        r2.setPower(power);
        l1.setPower(power);
        l2.setPower(power);
    }
    // spins left
    public void spinLeft(double power, double distance) {
        r1.setPower(-power);
        r2.setPower(-power);
        l1.setPower(-power);
        l2.setPower(-power);
    }
}
