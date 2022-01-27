package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Hardware;

@Autonomous(name = "CompAutoReal")
public class CompAutoReal extends LinearOpMode {
    DcMotor r1;
    DcMotor r2;
    DcMotor l1;
    DcMotor l2;
    DcMotor carousel;
    DcMotor intake;
    DcMotor slides;
    Servo drop;
    HardwareMap hwMap;

    int ticks = 538;
    int cWheelCircumference = 9;
    int ticksPerInch = ticks/cWheelCircumference;
    int carouselCircumference = 36;
    int mWheelcircumference = 10;
    int robotWidth = 11;

    int startToWall = 31;
    int freightToWall = 38;
    int wallToHub = 15;
    int hubToCarousel = 35;
    int wallToEnd = 20;

    double level1Height;
    double level2Height;
    int level3Height = 19;

    public void init(HardwareMap Map) {
        r1 = hardwareMap.get(DcMotor.class, "r1");
        r2 = hardwareMap.get(DcMotor.class, "r2");
        l1 = hardwareMap.get(DcMotor.class, "l1");
        l2 = hardwareMap.get(DcMotor.class, "l2");
        drop = hardwareMap.get(Servo.class, "Drop");
        carousel = hardwareMap.get(DcMotor.class, "Carousel");
        intake = hardwareMap.get(DcMotor.class, "Intake");
        slides = hardwareMap.get(DcMotor.class, "Linear Slides");

        r1.setDirection(DcMotor.Direction.REVERSE);
        r2.setDirection(DcMotor.Direction.REVERSE);
        l1.setDirection(DcMotor.Direction.FORWARD);
        l2.setDirection(DcMotor.Direction.FORWARD);
        //drop.setDirection(Servo.Direction.FORWARD);
        carousel.setDirection(DcMotor.Direction.REVERSE);
        intake.setDirection(DcMotor.Direction.FORWARD);
        slides.setDirection(DcMotor.Direction.FORWARD);

        r1.setPower(0);
        r2.setPower(0);
        l1.setPower(0);
        l2.setPower(0);
        //drop.setPosition(0);
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

        freightToHub(1.5, startToWall);
        toFreight(1);
        freightToHub(2, freightToWall);
        toCarousel(0.5);
        spinCarousel(carouselCircumference, 2.5);
        toLandingZone(0.5);
        stopMoving();


        telemetry.addData("Status", "End");
        telemetry.update();

        while (opModeIsActive()) {
            idle();
        }
    }

    public void spinCarousel(int distanceInInches, double timeout) {
        int distanceInTicks = distanceInInches * ticksPerInch;
        carousel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        carousel.setTargetPosition((int) distanceInTicks);
        carousel.setPower(0.1);

        if (opModeIsActive() && runtime.seconds() < timeout && intake.isBusy()) {
            while (opModeIsActive()) {
                telemetry.addData("Intake", "Turning %7d revolution(s)", distanceInInches/carouselCircumference);
                carousel.getCurrentPosition();
                telemetry.update();
            }
        }
        carousel.setPower(0);
    }

    public void spinIntake() {
        intake.setPower(1);
        sleep(3000);
        //drop.setPosition(0.15);
    }

    public void moveSlides(double timeout, int slidesHeight, double power) {
        /*slides.setPower(0.1);
        sleep(6000);
        slides.setPower(0);
        sleep(1000);
        slides.setPower(-0.1);
        sleep(4500);
        slides.setPower(0);*/

        int distanceInTicks = (int) (slidesHeight * ticksPerInch);
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
                slides.getCurrentPosition();
                telemetry.update();
            }
        }

        slides.setPower(0);

        //drop.setPosition(0.7);
        sleep(100);
        //drop.setPosition(0.1);

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
        //drop.setPosition(0);
    }

    // moves forward
    public void moveBackward(double power) {
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
    public void moveForward(double power) {
        r1.setPower(power);
        r2.setPower(power);
        l1.setPower(-power);
        l2.setPower(-power);
    }
    // spins rights
    public void spinRight(double power, int distance) {
        getTarget(distance);
        r1.setPower(power);
        r2.setPower(power);
        l1.setPower(power);
        l2.setPower(power);
    }
    // spins left
    public void spinLeft(double power, int distance) {
        getTarget(distance);
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
        carousel.setPower(0);
        intake.setPower(0);
        slides.setPower(0);
    }

    // calculates distance in ticks
    public int getTarget(int distanceInInches) {
        // distances --> startToFreight: 20, freightToWall: tomeasure, wallToHub: 56, wallToCarousel: tomeasure, wallToEnd: tomeasure
        int ticksPerInch = ticks/mWheelcircumference;
        int distanceInTicks = distanceInInches * ticksPerInch;

        r1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        r2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        l1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        l2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        r1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        r2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        l1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        l2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        r1.setTargetPosition((int) distanceInTicks);
        r2.setTargetPosition((int) distanceInTicks);
        l1.setTargetPosition((int) distanceInTicks);
        l2.setTargetPosition((int) distanceInTicks);

        return distanceInTicks;
    }

    // calculates angle in ticks
    public int getAngleDistance(int angle) {
        int circumferenceInInches = robotWidth * 3;
        int angleDistanceInInches = (angle/360) * circumferenceInInches;

        return angleDistanceInInches;
    }

    // start position to freight
    public void freightToHub(double timeout, int distance) {
        getTarget(distance);
        moveBackward(0.75);

        if (opModeIsActive() && runtime.seconds() < timeout && r1.isBusy() && r2.isBusy() && l1.isBusy() && l2.isBusy()) {
            while (opModeIsActive()) {
                telemetry.addData("Freight to Hub", "Moving Backward %7d ticks", getTarget(distance));
                r1.getCurrentPosition();
                r2.getCurrentPosition();
                l1.getCurrentPosition();
                l2.getCurrentPosition();
                telemetry.update();
            }
        }

        runtime.reset();
        timeout = 1;

        spinRight(0.5, getAngleDistance(90));

        if (opModeIsActive() && runtime.seconds() < timeout && r1.isBusy() && r2.isBusy() && l1.isBusy() && l2.isBusy()) {
            while (opModeIsActive()) {
                telemetry.addData("Freight to Hub", "Spinning Right %7d ticks", getTarget(getAngleDistance(90)));
                r1.getCurrentPosition();
                r2.getCurrentPosition();
                l1.getCurrentPosition();
                l2.getCurrentPosition();
                telemetry.update();
            }
        }

        runtime.reset();
        timeout = 0.3;

        getTarget(wallToHub);
        moveBackward(0.75);

        if (opModeIsActive() && runtime.seconds() < timeout && r1.isBusy() && r2.isBusy() && l1.isBusy() && l2.isBusy()) {
            while (opModeIsActive()) {
                telemetry.addData("Freight to Hub", "Moving Backward %7d ticks", getTarget(wallToHub));
                r1.getCurrentPosition();
                r2.getCurrentPosition();
                l1.getCurrentPosition();
                l2.getCurrentPosition();
                telemetry.update();
            }
        }

        moveSlides(6, level3Height, 0.2);

        stopMoving();
    }

    public void toFreight(double timeout) {
        getTarget(wallToHub);
        moveForward(0.75);

        if (opModeIsActive() && runtime.seconds() < timeout && r1.isBusy() && r2.isBusy() && l1.isBusy() && l2.isBusy()) {
            while (opModeIsActive()) {
                telemetry.addData("Hub to Freight", "Moving Forward %7d ticks", getTarget(wallToHub));
                r1.getCurrentPosition();
                r2.getCurrentPosition();
                l1.getCurrentPosition();
                l2.getCurrentPosition();
                telemetry.update();
            }
        }

        runtime.reset();
        timeout = 0.3;

        spinLeft(0.5, getAngleDistance(90));

        if (opModeIsActive() && runtime.seconds() < timeout && r1.isBusy() && r2.isBusy() && l1.isBusy() && l2.isBusy()) {
            while (opModeIsActive()) {
                telemetry.addData("Hub to Freight", "Spinning Left %7d ticks", getAngleDistance(90));
                r1.getCurrentPosition();
                r2.getCurrentPosition();
                l1.getCurrentPosition();
                l2.getCurrentPosition();
                telemetry.update();
            }
        }

        runtime.reset();
        timeout = 2;

        spinIntake();
        getTarget(freightToWall);
        moveForward(0.75);

        if (opModeIsActive() && runtime.seconds() < timeout && r1.isBusy() && r2.isBusy() && l1.isBusy() && l2.isBusy()) {
            while (opModeIsActive()) {
                telemetry.addData("Hub to Freight", "Moving Forward %7d ticks", getTarget(freightToWall));
                r1.getCurrentPosition();
                r2.getCurrentPosition();
                l1.getCurrentPosition();
                l2.getCurrentPosition();
                telemetry.update();
            }
        }

        stopMoving();
    }

    public void toCarousel(double timeout) {
        getTarget(hubToCarousel);
        spinRight(0.5, getAngleDistance(60));

        if (opModeIsActive() && runtime.seconds() < timeout && r1.isBusy() && r2.isBusy() && l1.isBusy() && l2.isBusy()) {
            while (opModeIsActive()) {
                telemetry.addData("Hub to Carousel", "Spinning Right %7d ticks", getAngleDistance(60));
                r1.getCurrentPosition();
                r2.getCurrentPosition();
                l1.getCurrentPosition();
                l2.getCurrentPosition();
                telemetry.update();
            }
        }

        runtime.reset();
        timeout = 3;

        spinIntake();
        getTarget(hubToCarousel);
        moveForward(0.75);

        if (opModeIsActive() && runtime.seconds() < timeout && r1.isBusy() && r2.isBusy() && l1.isBusy() && l2.isBusy()) {
            while (opModeIsActive()) {
                telemetry.addData("Start to Hub", "Moving Forward %7d ticks", getTarget(hubToCarousel));
                r1.getCurrentPosition();
                r2.getCurrentPosition();
                l1.getCurrentPosition();
                l2.getCurrentPosition();
                telemetry.update();
            }
        }

        stopMoving();
    }

    public void toLandingZone(double timeout) {
        getTarget(wallToEnd);
        moveRight(0.5);

        if (opModeIsActive() && runtime.seconds() < timeout && r1.isBusy() && r2.isBusy() && l1.isBusy() && l2.isBusy()) {
            while (opModeIsActive()) {
                telemetry.addData("Start to Hub", "Moving Right %7d ticks", getTarget(hubToCarousel));
                r1.getCurrentPosition();
                r2.getCurrentPosition();
                l1.getCurrentPosition();
                l2.getCurrentPosition();
                telemetry.update();
            }
        }

        stopMoving();
    }
    /*public void setSlideDistance(int levelToGo) {
        resetEncoder();
        if (levelToGo == 0) {
            moveSlides(level0Distance);
        }
        else if (levelToGo == 1) {
            moveSlides(level1Distance);
        }
        else if (levelToGo == 2){
            moveSlides(level2Distance);
        }
    }*/
}
