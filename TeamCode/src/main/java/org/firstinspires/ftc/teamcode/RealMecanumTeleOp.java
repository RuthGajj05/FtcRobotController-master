package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Real Mecanum TeleOp")
public class RealMecanumTeleOp extends LinearOpMode {

    private DcMotor r1;
    private DcMotor r2;
    private DcMotor l1;
    private DcMotor l2;

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        float vertical;
        float horizontal;
        float pivot;

        r1 = hardwareMap.get(DcMotor.class, "r1");
        r2 = hardwareMap.get(DcMotor.class, "r2");
        l1 = hardwareMap.get(DcMotor.class, "l1");
        l2 = hardwareMap.get(DcMotor.class, "l2");

        // Put initialization blocks here.
        r1.setDirection(DcMotorSimple.Direction.REVERSE);
        r2.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {
                vertical = -gamepad1.right_stick_y;
                horizontal = gamepad1.right_stick_x;
                pivot = gamepad1.left_stick_x;
                r1.setPower(-pivot + (vertical - horizontal));
                r2.setPower(-pivot + vertical + horizontal);
                l1.setPower(pivot + vertical + horizontal);
                l2.setPower(pivot + (vertical - horizontal));
                // Put loop blocks here.
                telemetry.update();
            }
        }
    }
}