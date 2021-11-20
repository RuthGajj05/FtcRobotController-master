package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name = "Bezos10 (Blocks to Java)")
public class MovementStuff3 extends LinearOpMode {

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
            // Top Left Diagonal
            TopRight.setPower(1);
            BottomRight.setPower(0);
            TopLeft.setPower(0);
            BottomLeft.setPower(1);
            sleep(2000);
            // Top Right Diagonal
            TopRight.setPower(0);
            BottomRight.setPower(-1);
            TopLeft.setPower(-1);
            BottomLeft.setPower(0);
            sleep(2000);
            //Bottom Left Diagonal
            TopRight.setPower(0);
            BottomRight.setPower(1);
            TopLeft.setPower(1);
            BottomLeft.setPower(0);
            sleep(2000);
            // Bottom Right Diagonal
            TopRight.setPower(-1);
            BottomRight.setPower(0);
            TopLeft.setPower(0);
            BottomLeft.setPower(-1);
            sleep(2000);
            // Forward
            TopRight.setPower(1);
            BottomRight.setPower(-1);
            TopLeft.setPower(-1);
            BottomLeft.setPower(1);
            sleep(2000);
            // Backwards
            TopRight.setPower(-1);
            BottomRight.setPower(1);
            TopLeft.setPower(1);
            BottomLeft.setPower(-1);
            sleep(2000);
            // Left
            TopLeft.setPower(1);
            BottomLeft.setPower(1);
            TopRight.setPower(1);
            BottomRight.setPower(1);
            sleep(2000);
            // Right
            TopRight.setPower(-1);
            BottomRight.setPower(-1);
            TopLeft.setPower(-1);
            BottomLeft.setPower(-1);
            sleep(2000);
            // Stop
            TopRight.setPower(0);
            BottomRight.setPower(0);
            TopLeft.setPower(0);
            BottomLeft.setPower(0);
        }
    }
}