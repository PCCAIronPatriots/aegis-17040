package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.HardwareMap


@TeleOp
 class Tele : OpMode() {
    override fun init() {
      var drive =  Drivetrain()
        drive.init(hardwareMap)
        drive.telem(telemetry)


    }
    override fun loop() {
        var lSticky = gamepad1.left_stick_y
        var lStickX = gamepad1.left_stick_x
        var rStickX = gamepad1.right_stick_x


        var drive = Drivetrain()
        drive.init(hardwareMap)
        drive.telem(telemetry)
        drive.run(gamepad1)

    }
}