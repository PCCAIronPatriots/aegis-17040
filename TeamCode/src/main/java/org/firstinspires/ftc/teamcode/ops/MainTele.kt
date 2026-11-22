package org.firstinspires.ftc.teamcode.ops

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import org.firstinspires.ftc.teamcode.mechanisms.Drive
import org.firstinspires.ftc.teamcode.mechanisms.Intake
import org.firstinspires.ftc.teamcode.mechanisms.Launcher
import org.firstinspires.ftc.teamcode.Team

abstract class MainTele(val team : Team?) : OpMode() {
     var launch = Launcher() // this includes the windmill
     var intake = Intake()
     var drive = Drive()
    /**
      Initializes follower, launcher, intake, and updates initial telemetry.
     */
    override fun init() { /**
    code that loops after driver presses "init" on driver hub and before driver presses "start"
    */
        drive.start(hardwareMap, telemetry)
        intake.start(hardwareMap, telemetry)
        launch.start(hardwareMap, telemetry)
        telemetry.update()
    }
    /**
     * Main teleop loop handling manual driving, intake, launcher controls, and telemetry.
     */
    override fun loop() { // code that loops after driver presses "play" on the driver hub
        drive.loop(gamepad1, telemetry)
        intake.loop(gamepad1, telemetry)
        launch.loop(gamepad1, telemetry)
        telemetry.update()
    }
}