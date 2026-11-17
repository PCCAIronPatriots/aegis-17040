package org.firstinspires.ftc.teamcode.ops

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.Intake
import org.firstinspires.ftc.teamcode.Launcher
import com.pedropathing.follower.Follower
import com.pedropathing.follower.ManualDrive
import com.pedropathing.math.Pose
import org.firstinspires.ftc.teamcode.pedro.Constants
import org.firstinspires.ftc.teamcode.Constants.ChassisSpeed

@TeleOp(name = "TeleOp", group = "OpMode")
class Tele : OpMode() {
    private lateinit var follower: Follower
    var launch = Launcher() // this includes the windmill
    var intake = Intake()

    override fun init() { /*
    code that loops after driver presses "init" on driver hub and before driver presses "start"
    */
        follower = Constants.create(hardwareMap)
        follower.setPose(Pose(0.0, 0.0, 0.0))
        launch.init(hardwareMap)
        intake.init(hardwareMap)
        launch.telem(telemetry)
        intake.telem(telemetry)
        telemetry.update()
    }

    override fun loop() { // code that loops after driver presses "play" on the driver hub
        ManualDrive.driveOrHold(
            follower,
            -gamepad1.left_stick_y.toDouble() * ChassisSpeed,
            -gamepad1.left_stick_x.toDouble() * ChassisSpeed,
            -gamepad1.right_stick_x.toDouble() * ChassisSpeed,
        )
        follower.update()

        intake.run(gamepad1)
        launch.control(gamepad1)
        intake.telem(telemetry)
        launch.telem(telemetry)
        telemetry.update()
    }
}