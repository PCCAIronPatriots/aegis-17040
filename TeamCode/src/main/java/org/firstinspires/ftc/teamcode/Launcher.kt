package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.Gamepad
import org.firstinspires.ftc.robotcore.external.Telemetry
import com.qualcomm.robotcore.hardware.HardwareMap




class Launcher {
    private lateinit var launcher: DcMotorEx
    val launcherSpeeds = arrayOf(0, 280, 560, 840, 1120, 1400, 1680, 1960, 2240, 2520, 2800)
    var speedSetting: Int = 0
    var curSelecLaunchVelo: Int = launcherSpeeds[speedSetting]
    var launcherRPM: Double = (launcher.getVelocity() / 28 * 60)

    fun init(hardwaremap: HardwareMap) {
        launcher = hardwaremap.get(DcMotorEx::class.java, "launcher")
        launcher.direction = DcMotorSimple.Direction.REVERSE
    }

    fun run(gamepad1: Gamepad) {
        val lTrig = gamepad1.left_trigger
        val rTrig = gamepad1.right_trigger
        val aButtonPressed = gamepad1.aWasPressed()
        val bButtonPressed = gamepad1.bWasPressed()



        if (aButtonPressed && speedSetting < launcherSpeeds.size - 1) {
            speedSetting++;
        }
        if (bButtonPressed && speedSetting > 0) {
            speedSetting--;
        }
        launcher.setVelocity((rTrig - lTrig) * curSelecLaunchVelo.toDouble());
    }
}