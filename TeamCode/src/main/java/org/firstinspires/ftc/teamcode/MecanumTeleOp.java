package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class MecanumTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor r1 = hardwareMap.dcMotor.get("r1");
        DcMotor r2 = hardwareMap.dcMotor.get("r2");
        DcMotor l1 = hardwareMap.dcMotor.get("l1");
        DcMotor l2 = hardwareMap.dcMotor.get("l2");

        r1.setDirection(DcMotorSimple.Direction.REVERSE);
        r2.setDirection(DcMotorSimple.Direction.REVERSE);
        l1.setDirection(DcMotorSimple.Direction.FORWARD);
        l2.setDirection(DcMotorSimple.Direction.FORWARD);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double ly = -gamepad1.left_stick_y;
            double lx = gamepad1.left_stick_x * 1.1;
            double rx = gamepad1.right_stick_x;

            double denominator = Math.max(Math.abs(ly) + Math.abs(lx) + Math.abs(rx), 1);
            double frontLeftPower = (ly + lx + rx) / denominator;
            double backLeftPower = (ly - lx + rx) / denominator;
            double frontRightPower = (ly - lx - rx) / denominator;
            double backRightPower = (ly + lx - rx) / denominator;

            r1.setPower(frontRightPower);
            r2.setPower(backRightPower);
            l1.setPower(frontLeftPower);
            l2.setPower(backLeftPower);

        }
    }
}

