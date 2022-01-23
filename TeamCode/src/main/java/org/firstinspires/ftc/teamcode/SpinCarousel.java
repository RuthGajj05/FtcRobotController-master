package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Hardware;

@Autonomous
public class SpinCarousel extends LinearOpMode {
    DcMotor carousel;
    DcMotor intake;
    DcMotor slides;
    HardwareMap hwMap;
    double ticks = 537.7;
    double cWheelCircumference = 3 * 3.14;
    double ticksPerInch = ticks/cWheelCircumference;
    double carouselCircumference = 11.45 * 3.14;

    double level1Height;
    double level2Height;
    double level3Height;

    public void init(HardwareMap Map) {
        carousel = hardwareMap.get(DcMotor.class, "Carousel");
        intake = hardwareMap.get(DcMotor.class, "Intake");
        slides = hardwareMap.get(DcMotor.class, "Linear Slides");

        carousel.setDirection(DcMotor.Direction.REVERSE);
        intake.setDirection(DcMotor.Direction.FORWARD);
        slides.setDirection(DcMotor.Direction.FORWARD);

        carousel.setPower(0);
        intake.setPower(0);
        slides.setPower(0);

        carousel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slides.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

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
        moveSlides(6, 0);

        telemetry.addData("Status", "End");
        telemetry.update();

        while (opModeIsActive()) {
            idle();
        }
    }

    public void spinCarousel(double distanceInInches, double timeout) {
        // error here
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

    public void moveSlides(double timeout, double intakeHeight) {
        /*slides.setPower(0.1);
        sleep(6000);
        slides.setPower(0);
        sleep(1000);
        slides.setPower(-0.1);
        sleep(4500);
        slides.setPower(0);*/

        double distanceInTicks = intakeHeight * ticksPerInch;
        carousel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        carousel.setTargetPosition((int) distanceInTicks);
        carousel.setPower(0.1);

        slides.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slides.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slides.setTargetPosition(537);
        slides.setPower(0.1);
        if (opModeIsActive() && runtime.seconds() < timeout && intake.isBusy()) {
            while (opModeIsActive()) {
                telemetry.addData("Slides", "Moving to Level %7d, %7d height", intakeHeight, distanceInTicks);
                intake.getCurrentPosition();
                telemetry.update();
            }
        }

        slides.setPower(0);

        slides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slides.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slides.setTargetPosition(537);
        slides.setPower(-0.1);
        if (opModeIsActive() && runtime.seconds() < timeout && intake.isBusy()) {
            while (opModeIsActive()) {
                telemetry.addData("Slides", "Moving to Level %7d, %7d height", intakeHeight, distanceInTicks);
                intake.getCurrentPosition();
                telemetry.update();
            }
        }

        slides.setPower(0);
    }


}
