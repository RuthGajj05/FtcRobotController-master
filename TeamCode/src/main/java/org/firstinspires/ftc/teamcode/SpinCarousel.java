package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
@Autonomous
public class SpinCarousel extends LinearOpMode {
    DcMotor carousel = null;
    HardwareMap hwMap = null;
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
                spinCarousel();
            }
        }

    }

    public void spinCarousel() {
        carousel.setPower(0.5);
    }
}
