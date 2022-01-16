package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "GamepadDrive1 (Blocks to Java)")
public class RealMecanumTeleOp extends LinearOpMode {

    private DcMotor TopRight;
    private DcMotor BottomRight;
    private DcMotor TopLeft;
    private DcMotor BottomLeft;

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        float vertical;
        float horizontal;
        float pivot;

        TopRight = hardwareMap.get(DcMotor.class, "r1");
        BottomRight = hardwareMap.get(DcMotor.class, "r2");
        TopLeft = hardwareMap.get(DcMotor.class, "l1");
        BottomLeft = hardwareMap.get(DcMotor.class, "l2");

        // Put initialization blocks here.
        TopRight.setDirection(DcMotorSimple.Direction.REVERSE);
        BottomRight.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {
                vertical = -gamepad1.right_stick_y;
                horizontal = gamepad1.right_stick_x;
                pivot = gamepad1.left_stick_x;
                TopRight.setPower(-pivot + (vertical - horizontal));
                BottomRight.setPower(-pivot + vertical + horizontal);
                TopLeft.setPower(pivot + vertical + horizontal);
                BottomLeft.setPower(pivot + (vertical - horizontal));
                // Put loop blocks here.
                telemetry.update();
            }
        }
    }
}