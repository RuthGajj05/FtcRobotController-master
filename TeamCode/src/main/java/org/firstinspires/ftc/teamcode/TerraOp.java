package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Basic: Iterative OpMode", group="Iterative Opmode")
@Disabled
public class TerraOp extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();
    TerraBot bot = new TerraBot();

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void init_loop() {
        bot.init(hardwareMap);
    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {
        bot.move(gamepad1.left_stick_y, gamepad1.right_stick_x);
    }

    @Override
    public void stop() {
    }

}

