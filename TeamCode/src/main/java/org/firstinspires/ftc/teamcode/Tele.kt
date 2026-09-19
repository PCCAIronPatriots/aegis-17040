package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.HardwareMap

@TeleOp
class Tele : OpMode() {
    override fun init() {
        var drive = SwerveDrive()
        drive.init(hardwareMap)
        drive.telem(telemetry)

    }
    override fun loop() {
        var drive = SwerveDrive()
        drive.init(hardwareMap)
        drive.telem(telemetry)
        telemetry.update()
        drive.run(gamepad1)

    }
}