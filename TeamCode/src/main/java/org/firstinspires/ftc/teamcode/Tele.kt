package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.HardwareMap

@TeleOp
class Tele : OpMode() {
    var drive = ArcadeDrive()
    var launch = Launcher()
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
//        drive.init(hardwareMap)
//        launch.init(hardwareMap)
//        intake.init(hardwareMap)
        drive.run(gamepad1)
        launch.control(gamepad1)
        intake.run(gamepad1)

        drive.telem(telemetry)
        intake.telem(telemetry)
        launch.telem(telemetry)
        telemetry.update()

    }
}