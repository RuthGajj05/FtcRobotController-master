package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

@Autonomous
public class FinalCompAuto extends LinearOpMode {
    // initialize motors :(
    DcMotor r1 = null;
    DcMotor r2 = null;
    DcMotor l1 = null;
    DcMotor l2 = null;
    DcMotor carousel = null;
    DcMotor intake = null;
    Servo drop = null;
    DcMotor linearSlides = null;
    HardwareMap hwMap;

    // declare variables (¡Lo siento!)
    double dropStart = 0.6;
    double dropRiseFall = dropStart + 0.1;
    double dropEnd = 0;

    // declare vuforia and tf
    private static final String TFOD_MODEL_ASSET = "FreightFrenzy_BCDM.tflite";
    private static final String[] LABELS = {
            "Ball",
            "Cube",
            "Duck",
            "Marker"
    };

    private static final String VUFORIA_KEY =
            "Aa8KwUr/////AAABmfSOBqzrbEvniVWLAXIqPwJzl85aK5NvpO9RrwrVGU7bgvw38PAbJEz7liwhgQHULMRyD5Cr3e/rfVgdM+knxtaVNkEuo3AH0+RoQ3BQ9xDoq/G3EGyajkpidEExIhA6Mt1YwaJSpsz5dkC5vk5MMxKN11NY+qVobu/KSdf4WTEa78IMN8jc/56CBzqXojUAzyvQqesYZk5V36unDyzDnRAbXHKr1oo22ICmNXrcU9pTLiBwcfnnc/anF7LqvOPhQtqA4lEgdQBgw2QttEjGp/DrxJ4iGQPPVnQWen0Nx5K+DPWg2A9U+exAqIL5j1Zy9eupOYVmwB6egvNP3AUnbLhqKsawcT0l8tRt16aq2zd9";

    private VuforiaLocalizer vuforia;

    private TFObjectDetector tfod;

    boolean isMarkerDetected = false;

    double timeout = 3;

    // init code
    public void init(HardwareMap Map) {
        r1 = hardwareMap.get(DcMotor.class, "r1");
        r2 = hardwareMap.get(DcMotor.class, "r2");
        l1 = hardwareMap.get(DcMotor.class, "l1");
        l2 = hardwareMap.get(DcMotor.class, "l2");
        carousel = hardwareMap.get(DcMotor.class, "Carousel");
        intake = hardwareMap.get(DcMotor.class, "Intake");
        drop = hardwareMap.get(Servo.class, "Drop");
        linearSlides = hardwareMap.get(DcMotor.class, "Linear Slides");

        r1.setDirection(DcMotor.Direction.REVERSE);
        r2.setDirection(DcMotor.Direction.REVERSE);
        l1.setDirection(DcMotor.Direction.FORWARD);
        l2.setDirection(DcMotor.Direction.FORWARD);
        carousel.setDirection(DcMotor.Direction.FORWARD);
        intake.setDirection(DcMotor.Direction.REVERSE);
        drop.setDirection(Servo.Direction.FORWARD);
        linearSlides.setDirection(DcMotor.Direction.FORWARD);

        r1.setPower(0);
        r2.setPower(0);
        l1.setPower(0);
        l2.setPower(0);
        carousel.setPower(0);
        intake.setPower(0);
        dropStart = drop.getPosition();
        linearSlides.setPower(0);

        r1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        r2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        l1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        l2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        carousel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        linearSlides.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        r1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        r2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        l1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        l2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        carousel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //drop.resetDeviceConfigurationForOpMode();
        linearSlides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    // elapsed time function
    private ElapsedTime runtime = new ElapsedTime();
    //VuforiaObjectDetection webcam = new VuforiaObjectDetection();

    // code to run while play
    @Override
    public void runOpMode() {
        init(hwMap);
        //initVuforia();
        //initTfod();
        if (tfod != null) {
            tfod.activate();
            tfod.setZoom(2.5, 16.0/9.0);
        }
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        runtime.reset();
        waitForStart();

        //moveForward(0.5,1000); = 22.5 inches
        //moveRight(0.2, 1000); = 14.5 inches

        // 4 right
        moveRight(0.2, 426);
        telemetry.addData("Status", "Move Right");
        telemetry.update();
        stopMoving(20);
        telemetry.addData("Status", "Stop :)");
        telemetry.update();

        // 48.5 forward
        moveForward(0.5, 2156);
        telemetry.addData("Status", "Move Forward");
        telemetry.update();
        stopMoving(20);
        telemetry.addData("Status", "Stop :)");
        telemetry.update();

        //carousel
        spinCarousel(3000);
        telemetry.addData("Status", "Spin Carousel");
        telemetry.update();
        stopMoving(20);
        telemetry.addData("Status", "Stop :)");
        telemetry.update();

        // 43.5 backward
        moveBackward(0.5, 1900);
        telemetry.addData("Status", "Move Backward");
        telemetry.update();
        stopMoving(20);
        telemetry.addData("Status", "Stop :)");
        telemetry.update();

        // 4 right
        moveRight(0.2, 526);
        telemetry.addData("Status", "Move Right");
        telemetry.update();
        stopMoving(20);
        telemetry.addData("Status", "Stop :)");
        telemetry.update();

        // 90 deg spinright
        spinLeft(0.6, 687);
        telemetry.addData("Status", "Spin Right");
        telemetry.update();
        stopMoving(20);
        telemetry.addData("Status", "Stop :)");
        telemetry.update();
        runtime.reset();
            /*while (runtime.seconds() < timeout) {
                if (tfod != null) {
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                        telemetry.addData("# Object Detected", updatedRecognitions.size());
                        int i = 0;
                        for (Recognition recognition : updatedRecognitions) {
                            telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
                            telemetry.addData(String.format("  left,top (%d)", i), "%.03f , %.03f",
                                    recognition.getLeft(), recognition.getTop());
                            telemetry.addData(String.format("  right,bottom (%d)", i), "%.03f , %.03f",
                                    recognition.getRight(), recognition.getBottom());
                            i++;

                            if (recognition.getLabel().equals("Marker")) {
                                isMarkerDetected = true;
                                telemetry.addData("Object Detected", "Marker");
                                sleep(10);
                                break;
                            }
                            else if (recognition.getLabel().equals("Cube") || recognition.getLabel().equals("Ball") || recognition.getLabel().equals("Duck")){
                                isMarkerDetected = false;
                                telemetry.addData("Object Detected", "Shipping Element");
                                break;
                            }
                            else {
                                isMarkerDetected = true;
                            }
                            telemetry.update();
                        }
                        telemetry.update();
                    }
                    if (isMarkerDetected == true) {
                        break;
                    }
                }
            }*/

        // 11 left if detect
        if (isMarkerDetected == true) {
            //11 inches left
            moveRight(0.2, 859);
            telemetry.addData("Status", "Move Left");
            telemetry.update();
            stopMoving(20);
            telemetry.addData("Status", "Stop :)");
            telemetry.update();

            // 7 forward
            moveBackward(0.5, 450);
            telemetry.addData("Status", "Move Forward");
            telemetry.update();
            stopMoving(20);
            telemetry.addData("Status", "Stop :)");
            telemetry.update();

            //linearSlides
            servoTest();
            telemetry.addData("Status", "Linear Slides and Servo");
            telemetry.update();
            stopMoving(20);
            telemetry.addData("Status", "Stop :)");
            telemetry.update();

        } else {
            moveLeft(0.2, 445);
            telemetry.addData("Status", "Move Forward");
            telemetry.update();
            stopMoving(20);
            telemetry.addData("Status", "Stop :)");
            telemetry.update();

            runtime.reset();
            isMarkerDetected = false;
            /*while (runtime.seconds() < timeout) {
                if (tfod != null) {
                    tfod.activate();
                    tfod.setZoom(2.5, 16.0/9.0);
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                        telemetry.addData("# Object Detected", updatedRecognitions.size());
                        int i = 0;
                        for (Recognition recognition : updatedRecognitions) {
                            telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
                            telemetry.addData(String.format("  left,top (%d)", i), "%.03f , %.03f",
                                    recognition.getLeft(), recognition.getTop());
                            telemetry.addData(String.format("  right,bottom (%d)", i), "%.03f , %.03f",
                                    recognition.getRight(), recognition.getBottom());
                            i++;

                            if (recognition.getLabel().equals("Marker")) {
                                isMarkerDetected = true;
                                telemetry.addData("Object Detected", "Marker");
                                sleep(10);
                                break;
                            }
                            else if (recognition.getLabel().equals("Cube") || recognition.getLabel().equals("Ball") || recognition.getLabel().equals("Duck")){
                                isMarkerDetected = false;
                                telemetry.addData("Object Detected", "Shipping Element");
                                break;
                            }
                            else {
                                isMarkerDetected = true;
                            }
                        }
                        telemetry.update();
                    }
                    if (isMarkerDetected == true) {
                        break;
                    }
                }
            }*/

            if (isMarkerDetected == false) {
                //11 inches left
                moveRight(0.2, 1000);
                telemetry.addData("Status", "Move Left");
                telemetry.update();
                stopMoving(20);
                telemetry.addData("Status", "Stop :)");
                telemetry.update();

                // 7 forward
                moveBackward(0.5, 550);
                telemetry.addData("Status", "Move Forward");
                telemetry.update();
                stopMoving(20);
                telemetry.addData("Status", "Stop :)");
                telemetry.update();

                //linearSlides
                servoTest();
                telemetry.addData("Status", "Linear Slides and Servo");
                telemetry.update();
                stopMoving(20);
                telemetry.addData("Status", "Stop :)");
                telemetry.update();

            } else {
                moveRight(0.2, 1200);
                telemetry.addData("Status", "Move Left");
                telemetry.update();
                stopMoving(20);
                telemetry.addData("Status", "Stop :)");
                telemetry.update();

                // 7 forward
                moveBackward(0.5, 650);
                telemetry.addData("Status", "Move Forward");
                telemetry.update();
                stopMoving(20);
                telemetry.addData("Status", "Stop :)");
                telemetry.update();

                //linearSlides
                servoTest();
                telemetry.addData("Status", "Linear Slides and Servo");
                telemetry.update();
                stopMoving(20);
                telemetry.addData("Status", "Stop :)");
                telemetry.update();
            }
        }

        //
        moveForward(0.5, 150);
        telemetry.addData("Status", "Move Backward");
        telemetry.update();
        stopMoving(20);
        telemetry.addData("Status", "Stop :)");
        telemetry.update();

        //
        spinLeft(0.6, 687);
        telemetry.addData("Status", "Move Backward");
        telemetry.update();
        stopMoving(20);
        telemetry.addData("Status", "Stop :)");
        telemetry.update();

        moveRight(0.2, 700);
        telemetry.addData("Status", "Move Backward");
        telemetry.update();
        stopMoving(20);
        telemetry.addData("Status", "Stop :)");
        telemetry.update();
        //
        moveForward(0.5, 2000);
        telemetry.addData("Status", "Move Backward");
        telemetry.update();
        stopMoving(20);
        telemetry.addData("Status", "Stop :)");
        telemetry.update();

        while (opModeIsActive()) {
            telemetry.addData("Status", runtime.milliseconds());
        }
    }

    public void servoTest() {
        drop.setPosition(0.8);
        sleep(500);
        linearSlides.setPower(0.1);
        sleep(3000);
        linearSlides.setPower(0);
        sleep(1000);
        drop.setPosition(0.2);
        sleep(1000);
        drop.setPosition(0.8);
        sleep(1000);
        linearSlides.setPower(-0.05);
        sleep(3000);
        linearSlides.setPower(0);
        drop.setPosition(0.9);
        sleep(2000);
    }

    public void spinRight(double power, int time) {
        r1.setPower(-power);
        r2.setPower(-power);
        l1.setPower(power);
        l2.setPower(power);
        sleep(time);
    }

    // moves right
    public void moveRight(double power, int time) {
        r1.setPower(power);
        r2.setPower(-power);
        l1.setPower(power);
        l2.setPower(-power);
        sleep(time);
    }

    // move lefts
    public void moveLeft(double power, int time) {
        r1.setPower(-power);
        r2.setPower(power);
        l1.setPower(-power);
        l2.setPower(power);
        sleep(time);
    }

    // moves backward
    public void spinLeft(double power, int time) {
        r1.setPower(power);
        r2.setPower(power);
        l1.setPower(-power);
        l2.setPower(-power);
        sleep(time);
    }

    // spins rights
    public void moveForward(double power, int time) {
        r1.setPower(power);
        r2.setPower(power);
        l1.setPower(power);
        l2.setPower(power);
        sleep(time);
    }

    // spins left
    public void moveBackward(double power, int time) {
        r1.setPower(-power);
        r2.setPower(-power);
        l1.setPower(-power);
        l2.setPower(-power);
        sleep(time);
    }

    // stop moving
    public void stopMoving(int time) {
        r1.setPower(0);
        r2.setPower(0);
        l1.setPower(0);
        l2.setPower(0);
        carousel.setPower(0);
        intake.setPower(0);
        linearSlides.setPower(0);
        sleep(time);
    }

    // spin carousel
    //prob 3
    public void spinCarousel(int time) {
        carousel.setPower(-0.1);
        sleep(time);
    }

    // init vuforia
    private void initVuforia() {
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");

        vuforia = ClassFactory.getInstance().createVuforia(parameters);

    }

    // init tfod
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.8f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 320;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);
    }
}
