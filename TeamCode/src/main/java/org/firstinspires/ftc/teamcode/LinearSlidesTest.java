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
    DcMotor carousel;
    DcMotor intake;
    DcMotor linearSlides;
    HardwareMap hwMap;
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

        carousel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        linearSlides.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


    }
    CompAuto auto = new CompAuto();
    private ElapsedTime runtime = new ElapsedTime();
    public void runOpMode() {
        waitForStart();
        runtime.reset();
        while (opModeIsActive()) {
            runtime.reset();
            while (runtime.milliseconds() < 20000) {
                intake();
                sleep(10000);
                stopMoving();
            }
            /*while (runtime.milliseconds() < 40000){
                moveSlides();
                sleep(5000);
                auto.stopMoving();
            }*/
            while (runtime.milliseconds() < 40000) {
                carousel();
                sleep(10000);
                stopMoving();

            }
        }
    }
    public void moveSlides() {
        auto.moveSlides(2);
    }
    public void intake() {
        intake.setPower(1);
    }
    public void carousel() {
        carousel.setPower(0.5);
    }
    public void stopMoving() {
        carousel.setPower(0);
        intake.setPower(0);
        linearSlides.setPower(0);
    }
}
