package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class MecanumAuto extends LinearOpMode{

    DcMotor r1 = null;
    DcMotor r2 = null;
    DcMotor l1 = null;
    DcMotor l2 = null;
    DcMotor carousel = null;
    HardwareMap hwMap = null;

    double startToFreight = 20;
    double freightToWall = 59;
    double wallToHub = 56;
    double wallToCarousel = 118;
    double wallToEnd;

    double robotLength;
    double robotWidth;

    double diameterCarouselWheel = 4;

    public void init(HardwareMap Map) {
        r1 = hwMap.get(DcMotor.class, "r1");
        r2 = hwMap.get(DcMotor.class, "r2");
        l1 = hwMap.get(DcMotor.class, "l1");
        l2 = hwMap.get(DcMotor.class, "l2");
        carousel = hwMap.get(DcMotor.class, "Carousel");

        r1.setDirection(DcMotor.Direction.REVERSE);
        r2.setDirection(DcMotor.Direction.REVERSE);
        l1.setDirection(DcMotor.Direction.FORWARD);
        l2.setDirection(DcMotor.Direction.FORWARD);
        carousel.setDirection(DcMotor.Direction.FORWARD);

        r1.setPower(0);
        r2.setPower(0);
        l1.setPower(0);
        l2.setPower(0);
        carousel.setPower(0);

        r1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        r2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        l1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        l2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        carousel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        r1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        r2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        l1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        l2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        carousel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();
        runtime.reset();
        startToFreight();
        toHub();

        while (opModeIsActive()) {
            runtime.reset();
            while (runtime.milliseconds() < 20000) {
                backToFreight();
                toHub();
            }
        }

        toCarousel();
        spinCarousel();
        toLandingZone();
    }

    public void resetEncoder() {
        r1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        r2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        l1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        l2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void runToPosition() {
        r1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        r2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        l1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        l2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void getTarget(double distanceInInches) {
        // distances --> startToFreight: 20, freightToWall: tomeasure, wallToHub: 56, wallToCarousel: tomeasure, wallToEnd: tomeasure
        double ticks = 537.7;
        double circumference = 11.87;
        double ticksPerInch = ticks/circumference;
        double distanceInTicks = distanceInInches * ticksPerInch;

        r1.setTargetPosition((int) distanceInTicks);
        r2.setTargetPosition((int) distanceInTicks);
        l1.setTargetPosition((int) distanceInTicks);
        l2.setTargetPosition((int) distanceInTicks);
    }

    public double getAngleDistance(double angle) {
        double ticks = 537.7;
        double circumferenceInInches = robotWidth * 3.14;
        double angleDistanceInInches = (angle/360) * circumferenceInInches;

        return angleDistanceInInches;
    }

    public void moveForward(double power) {
        r1.setPower(power);
        r2.setPower(-power);
        l1.setPower(-power);
        l2.setPower(power);
    }

    public void moveRight(double power) {
        r1.setPower(power);
        r2.setPower(-power);
        l1.setPower(power);
        l2.setPower(-power);
    }

    public void moveLeft(double power) {
        r1.setPower(-power);
        r2.setPower(power);
        l1.setPower(-power);
        l2.setPower(power);
    }

    public void moveBackward(double power) {
        r1.setPower(power);
        r2.setPower(power);
        l1.setPower(-power);
        l2.setPower(-power);
    }

    public void spinRight(double power, double distance) {
        resetEncoder();
        getTarget(distance);
        runToPosition();

        r1.setPower(power);
        r2.setPower(power);
        l1.setPower(power);
        l2.setPower(power);
    }

    public void spinLeft(double power, double distance) {
        resetEncoder();
        getTarget(distance);
        runToPosition();

        r1.setPower(-power);
        r2.setPower(-power);
        l1.setPower(-power);
        l2.setPower(-power);
    }

    public void stopMoving() {
        r1.setPower(0);
        r2.setPower(0);
        l1.setPower(0);
        l2.setPower(0);
    }

    public void spinCarousel() {
        double ticks = 537.7;
        double circumference = diameterCarouselWheel * 3.14;
        double ticksPerInch = ticks/circumference;
        double distanceInTicks = circumference * ticksPerInch * 1.59;

        carousel.setTargetPosition((int) distanceInTicks);
    }

    public void startToFreight() {
        resetEncoder();
        getTarget(startToFreight);
        runToPosition();
        moveForward(0.75);
        while (r1.isBusy() && r2.isBusy() && l1.isBusy() && l2.isBusy()) {
            // do nothing :)
        }
        stopMoving();
    }

    public void toHub() {
        resetEncoder();
        getTarget(freightToWall);
        runToPosition();
        moveBackward(0.75);
        while (r1.isBusy() && r2.isBusy() && l1.isBusy() && l2.isBusy()) {
            // do nothing :)
        }

        spinLeft(0.5, getAngleDistance(90));

        resetEncoder();
        getTarget(wallToHub);
        runToPosition();
        moveLeft(0.75);
        while (r1.isBusy() && r2.isBusy() && l1.isBusy() && l2.isBusy()) {
            // do nothing :)
        }
        stopMoving();
    }

    public void backToFreight() {
        resetEncoder();
        getTarget(wallToHub);
        runToPosition();
        moveForward(0.75);
        while (r1.isBusy() && r2.isBusy() && l1.isBusy() && l2.isBusy()) {
            // do nothing :)
        }

        spinRight(0.5, getAngleDistance(90));

        resetEncoder();
        getTarget(freightToWall);
        runToPosition();
        moveBackward(0.75);
        while (r1.isBusy() && r2.isBusy() && l1.isBusy() && l2.isBusy()) {
            // do nothing :)
        }

    }

    public void toCarousel() {
        resetEncoder();
        getTarget(wallToHub);
        runToPosition();
        moveForward(0.75);
        while (r1.isBusy() && r2.isBusy() && l1.isBusy() && l2.isBusy()) {
            // do nothing :)
        }

        spinRight(0.5, getAngleDistance(90));

        resetEncoder();
        getTarget(wallToCarousel);
        runToPosition();
        moveBackward(0.75);
        while (r1.isBusy() && r2.isBusy() && l1.isBusy() && l2.isBusy()) {
            // do nothing :)
        }
        stopMoving();

        spinCarousel();
    }

    public void toLandingZone() {
        resetEncoder();
        getTarget(wallToEnd);
        runToPosition();
        moveLeft(0.75);
        while (r1.isBusy() && r2.isBusy() && l1.isBusy() && l2.isBusy()) {
            // do nothing :)
        }
        stopMoving();
    }
}
