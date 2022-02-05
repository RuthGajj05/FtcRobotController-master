package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.hardware.Servo;


public class TerraBot {

    private DcMotor TopRight;
    private DcMotor TopLeft;
    private DcMotor BottomRight;
    private DcMotor BottomLeft;
    private Servo Arm;
    private DcMotor DuckSpinner;
    private DcMotor intake;
    private  DcMotor linearSlides;

    HardwareMap hwMap = null;

    public void init(HardwareMap Map){
        TopRight = Map.get(DcMotor.class, "r1");
        TopLeft = Map.get(DcMotor.class, "l1");
        BottomRight = Map.get(DcMotor.class, "r2");
        BottomLeft = Map.get(DcMotor.class, "l2");
        Arm = Map.get(Servo.class, "Drop");

        Arm.setDirection(Servo.Direction.REVERSE);
        Arm.setPosition(0.1);


        DuckSpinner = Map.get(DcMotor.class, "Carousel");
        intake = Map.get(DcMotor.class, "Intake");
        linearSlides = Map.get(DcMotor.class, "Linear Slides");

        TopLeft.setDirection(DcMotor.Direction.REVERSE);
        BottomRight.setDirection(DcMotor.Direction.FORWARD);
        TopRight.setDirection(DcMotor.Direction.FORWARD);
        BottomLeft.setDirection(DcMotor.Direction.REVERSE);

        DuckSpinner.setDirection(DcMotor.Direction.REVERSE);
        intake.setDirection(DcMotor.Direction.REVERSE);
        linearSlides.setDirection(DcMotor.Direction.FORWARD);


        TopLeft.setPower(0);
        BottomLeft.setPower(0);
        TopRight.setPower(0);
        BottomRight.setPower(0);
        DuckSpinner.setPower(0);
        intake.setPower(0);

        linearSlides.setPower(0);

        TopLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BottomLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        TopRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BottomRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        DuckSpinner.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        linearSlides.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        TopLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BottomLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        TopRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BottomRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        DuckSpinner.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        linearSlides.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);




    }

    public void move(double power, double offset){

        TopRight.setPower(power + offset);
        BottomRight.setPower(power - offset);
        BottomLeft.setPower(power + offset);
        TopLeft.setPower(power - offset);

    }
    public void sides(double power){
        TopRight.setPower(-power);
        BottomLeft.setPower(power);
        BottomRight.setPower(power);
        TopLeft.setPower(-power);
    }
    public void turn(double power){
        TopRight.setPower(power);
        BottomRight.setPower(power);
        TopLeft.setPower(-power);
        BottomLeft.setPower(-power);
    }
    public void stop(){
        move(0, 0);
    }

    public void servoThing(double pos){
        Arm.setPosition(pos);
    }

    public void duckThing(){
        DuckSpinner.setPower(0.5);
    }

    public void duckStop(){
        DuckSpinner.setPower(0);
    }

    public void intakeM(double power){
        intake.setPower(power);
    }

    public double armThing(){
        return Arm.getPosition();
    }
    // 537
    public void linearSlidesM(double power){
        linearSlides.setPower(power);
    }
}
