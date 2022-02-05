package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "EncoderTesting")
public class EncoderTesting extends LinearOpMode {

    private DcMotor r1;
    private DcMotor r2;
    private DcMotor l1;
    private DcMotor l2;

    private int left1Pos;
    private int left2Pos;
    private int right1Pos;
    private int right2Pos;

    @Override
    public void runOpMode() {
        r1 = hardwareMap.get(DcMotor.class, "r1");
        r2 = hardwareMap.get(DcMotor.class, "r2");
        l1 = hardwareMap.get(DcMotor.class, "l1");
        l2 = hardwareMap.get(DcMotor.class, "l2");

        r1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        r2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        l1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        l2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        r1.setDirection(DcMotor.Direction.REVERSE);
        r2.setDirection(DcMotor.Direction.REVERSE);
        l1.setDirection(DcMotor.Direction.FORWARD);
        l2.setDirection(DcMotor.Direction.FORWARD);

        left1Pos = 0;
        left2Pos = 0;
        right1Pos = 0;
        right2Pos = 0;

        waitForStart();
        
        drive(1000, 1000, 1000, 1000, 0.25);
        drive(1000, -1000, 1000, -1000, 0.25);  
    }

    private void drive(int left1Target, int right1Target, int left2Target, int right2Target, double speed) {
        left1Pos += left1Target;
        left1Pos += left2Target;
        right1Pos += right1Target;
        right2Pos += right2Target;

        l1.setTargetPosition(left1Pos);
        l2.setTargetPosition(left2Pos);
        r1.setTargetPosition(right1Pos);
        r2.setTargetPosition(right2Pos);

        l1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        l2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        r1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        r2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        l1.setPower(speed);
        l2.setPower(speed);
        r1.setPower(speed);
        r2.setPower(speed);

        while(opModeIsActive() && l1.isBusy() && l2.isBusy() && r1.isBusy() && r2.isBusy()) {
            idle();
        }
    }
}
