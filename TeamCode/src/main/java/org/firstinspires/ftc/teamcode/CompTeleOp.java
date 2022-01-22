package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.ArrayList;
import java.util.List;


@TeleOp(name="Controller", group="Linear Opmode")
//@Disabled
public class CompTeleOp extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    CompTeleBot bot = new CompTeleBot();


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
        while (opModeIsActive()) {
            isPivoting = false;
            direction.clear();

            telemetry.addData("time", runtime.milliseconds());
            if (gamepad1.left_bumper){
                telemetry.addData("Duck plate", "is spinning");
//                bot.duckThing();
            }
            if (gamepad2.left_bumper){
                telemetry.addData("left bumper", "left bumper");
                bot.turn(1);
                isPivoting = true;

            }

            else if (gamepad2.right_bumper){
                telemetry.addData("right bumper", "right bumper");
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
                bot.move(0, 1);
            }
            if (gamepad2.left_stick_x < -0.4){
                direction.add("left");
                bot.move(0, -1);

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
