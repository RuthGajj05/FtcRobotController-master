package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import java.util.ArrayList;
import java.util.List;


@TeleOp(name="Controller", group="Linear Opmode")
//@Disabled
public class CompTeleOp extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    TerraBot bot = new TerraBot();


    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // wait for the game to start (driver presses START)
        bot.init(hardwareMap);
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        List<String> direction = new ArrayList<String>();
        Boolean isPivoting = false;
        Boolean isArm = false;
        double power = 1;
        while (opModeIsActive()) {
            isPivoting = false;
            direction.clear();

            // gamepad 1
            telemetry.addData("time", runtime.milliseconds());
            if (gamepad1.left_bumper){
                telemetry.addData("Duck plate", "is spinning");
                bot.duckThing();
            }

            else {
                bot.duckStop();
            }

            if (gamepad1.a){
                bot.intakeM(-1);
            }
            else if(gamepad1.y){
                bot.intakeM(1
                );
            }
            else{
                bot.intakeM(0);

            }

            if (gamepad1.right_bumper){
                telemetry.addData("Arm servo", "Arm Servo");
//                for (double i=0; i<=0.5; i+=0.05){
//                    bot.servoThing(i);
//                    telemetry.addData("servo", bot.armThing());
//                    telemetry.update();
//                    sleep(1000);
//                }
                bot.servoThing(0.6);
                sleep(1000);
                bot.servoThing(0.1);
                telemetry.addData("servo", bot.armThing());
                telemetry.update();
            }
            if (gamepad1.dpad_up){
                power  = (power*10 + 1)/10;
                power = Range.clip(power, 0, 1);
                sleep(500);
            }
            if (gamepad1.dpad_down){
                power = (power*10 - 1)/10;
                sleep(500);
                power = Range.clip(power, 0, 1);
            }
            telemetry.addData("Power", power);
            if (gamepad1.b){
                bot.servoThing(0.2);
                telemetry.addData("linear slides", "linear slides");
                bot.linearSlidesM(power);
            }

            else if (gamepad1.x){
                bot.servoThing(0.1);
                telemetry.addData("linear slides", "linear slides");
                bot.linearSlidesM(-0.5);
            }

            else{
                bot.linearSlidesM(0);
            }

            // gamepad 2
            if (gamepad2.right_bumper){
                telemetry.addData("right bumper", "left bumper");
                bot.turn(1);
                isPivoting = true;
            }

            else if (gamepad2.left_bumper){
                telemetry.addData("left bumper", "right bumper");
                bot.turn(-1);
                isPivoting = true;
            }

            if (gamepad2.left_stick_y > 0.4){
                direction.add("down");
                bot.move(1, 0);
            }

            if (gamepad2.left_stick_y < -0.4){
                direction.add("up");
                bot.move(-1, 0);
            }
            if (gamepad2.left_stick_x > 0.4){
                direction.add("right");
                bot.sides(1);
            }
            if (gamepad2.left_stick_x < -0.4){
                direction.add("left");
                bot.sides(-1);

            }

            if (direction.isEmpty() && isPivoting == false){
                bot.stop();
            }

            telemetry.addData("direction", direction);
            telemetry.update();
            runtime.reset();


        }
    }
}
