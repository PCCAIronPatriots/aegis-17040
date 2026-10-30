package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.Gamepad
import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.PIDFCoefficients
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.Constants.launcherSetVelocity


class Launcher {
    private lateinit var launcher: DcMotorEx
    var windmill = Windmill()


    fun init(hardwaremap: HardwareMap) {
        windmill.init(hardwaremap)
        launcher = hardwaremap.get(DcMotorEx::class.java, "launcher")
        launcher.direction = DcMotorSimple.Direction.FORWARD
        launcher.setPIDFCoefficients(
            DcMotor.RunMode.RUN_USING_ENCODER,
            PIDFCoefficients(40.0, 0.0, 0.0, 12.5)
        )

    }

    fun setPowerInVelocity(power: Double) {
        launcher.setVelocity(Constants.launcherTickPerRevolution * 100 * power * Constants.launcherSetSpeed)
    }

     fun control(gamepad1: Gamepad, ) {
         val windmillThreshold = (launcherSetVelocity * (Constants.windmillThresholdValue / 100))
         var power = 0.0
         if (gamepad1.right_trigger > 0.15 && gamepad1.left_trigger < 0.15) {
             power = 1.0
             if (launcher.getVelocity() >= windmillThreshold) {
                 windmill.run(1.0)
             }
             } else if (gamepad1.left_trigger > 0.15 && gamepad1.right_trigger < 0.15) {
                 power = -1.0
                 if (launcher.getVelocity() <= -windmillThreshold) {
                     windmill.run(-1.0)
                 }
             } else {
                 power = 0.0
                 windmill.run(0.0)
             }
             setPowerInVelocity(power)
         }


    fun telem(telemetry: Telemetry) {
        val launcherRPM: Double = (launcher.getVelocity() / 28 * 60)

        telemetry.addData("LauncherPower: ", launcher.getPower())
        telemetry.addData("LauncherRPM: ", launcherRPM)
    }
    }
