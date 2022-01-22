package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class CompTeleBot {

    private DcMotor TopRight;
    private DcMotor TopLeft;
    private DcMotor BottomRight;
    private DcMotor BottomLeft;
//    private DcMotor DuckSpinner;

    HardwareMap hwMap = null;

    public void init(HardwareMap Map){
        TopRight = Map.get(DcMotor.class, "r1");
        TopLeft = Map.get(DcMotor.class, "l1");
        BottomRight = Map.get(DcMotor.class, "r2");
        BottomLeft = Map.get(DcMotor.class, "l2");

//        DuckSpinner = Map.get(DcMotor.class, "Duck Spinner");

        TopLeft.setDirection(DcMotor.Direction.REVERSE);
        BottomRight.setDirection(DcMotor.Direction.FORWARD);
        TopRight.setDirection(DcMotor.Direction.REVERSE);
        BottomLeft.setDirection(DcMotor.Direction.REVERSE);

//        DuckSpinner.setDirection(DcMotor.Direction.FORWARD);

        TopLeft.setPower(0);
        BottomLeft.setPower(0);
        TopRight.setPower(0);
        BottomRight.setPower(0);
//        DuckSpinner.setPower(0);

        TopLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BottomLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        TopRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BottomRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        TopLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BottomLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        TopRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BottomRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);



    }

    public void move(double power, double offset){

        TopRight.setPower(power + offset);
        BottomRight.setPower(power - offset);
        BottomLeft.setPower(power + offset);
        TopLeft.setPower(power - offset);

    }
    public void turn(double power){
        TopRight.setPower(power);
        BottomRight.setPower(power);
        TopLeft.setPower(-power);
        BottomLeft.setPower(-power);
    }
    public void stop(){
        move(0, 0);
//        DuckSpinner.setPower(0);
    }

//    public void duckThing(){
//        DuckSpinner.setPower(0.5);
//    }

}
