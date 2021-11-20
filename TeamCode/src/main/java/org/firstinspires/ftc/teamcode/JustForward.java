package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name = "Bezos25 (Blocks to Java)")
public class JustForward extends LinearOpMode {

    private DcMotor TopRight;
    private DcMotor TopLeft;
    private DcMotor BottomRight;
    private DcMotor BottomLeft;

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        TopRight = hardwareMap.get(DcMotor.class, "Top Right");
        TopLeft = hardwareMap.get(DcMotor.class, "Top Left");
        BottomRight = hardwareMap.get(DcMotor.class, "Bottom Right");
        BottomLeft = hardwareMap.get(DcMotor.class, "Bottom Left");

        // Put initialization blocks here.
        TopRight.setDirection(DcMotorSimple.Direction.FORWARD);
        TopLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            // Forward
            TopRight.setPower(1);
            BottomRight.setPower(-1);
            TopLeft.setPower(-1);
            BottomLeft.setPower(1);
            sleep(120000);
            // Stop
            TopRight.setPower(0);
            BottomRight.setPower(0);
            TopLeft.setPower(0);
            BottomLeft.setPower(0);
        }
    }
}
