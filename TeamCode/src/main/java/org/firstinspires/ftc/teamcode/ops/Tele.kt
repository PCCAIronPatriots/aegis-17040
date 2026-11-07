package org.firstinspires.ftc.teamcode.ops

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.Intake
import org.firstinspires.ftc.teamcode.Launcher
import org.firstinspires.ftc.teamcode.MecanumDrive

@TeleOp(name = "TeleOp", group = "OpMode")
class Tele : OpMode() {
    var drive = MecanumDrive()
    var launch = Launcher() //this includes the windmill
    var intake = Intake()

    override fun init() {
        drive.init(hardwareMap)
        launch.init(hardwareMap)
        intake.init(hardwareMap)

        drive.telem(telemetry)
        launch.telem(telemetry)
        intake.telem(telemetry)
        telemetry.update()

    }
    override fun loop() {
        drive.run(gamepad1)
        launch.control(gamepad1)

        drive.telem(telemetry)
        intake.telem(telemetry)
        launch.telem(telemetry)
        telemetry.update()

    }
}