package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name="Basic: Linear OpMode", group="Linear Opmode")
public class TerraAuto extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();

    TerraBot bot = new TerraBot();
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        bot.init(hardwareMap);
        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            runtime.reset();
            while (runtime.milliseconds() < 500) {
                bot.move(1,0);
            }
        }
    }
}

