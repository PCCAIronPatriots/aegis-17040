package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.Gamepad
import org.firstinspires.ftc.robotcore.external.Telemetry
import com.qualcomm.robotcore.hardware.HardwareMap




class Launcher {
    private lateinit var launcher: DcMotorEx


    fun init(hardwaremap: HardwareMap) {
        launcher = hardwaremap.get(DcMotorEx::class.java, "launcher")
        launcher.direction = DcMotorSimple.Direction.FORWARD
    }

    fun setPowerInVelocity(power: Double) {
        launcher.setVelocity(Constants.launcherTickPerRevolution * 100 * power)
    }

    fun control(gamepad1: Gamepad) {
        var power = 0.0
        if (gamepad1.right_trigger > 0.15) {
            power = 1.0
        } else if (gamepad1.left_trigger > 0.15) {
            power = -1.0
        } else {
            power = 0.0
        }
        setPowerInVelocity(power)
    }

    fun telem(telemetry: Telemetry) {
        var launcherRPM: Double = (launcher.getVelocity() / 28 * 60)

        telemetry.addData("LauncherPower: ", launcher.getPower())
        telemetry.addData("LauncherRPM: ", launcherRPM)
    }
    }
