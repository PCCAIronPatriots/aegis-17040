package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.Gamepad
import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.PIDFCoefficients
import org.firstinspires.ftc.robotcore.external.Telemetry


class Launcher {
    private lateinit var launcher: DcMotorEx


    fun init(hardwaremap: HardwareMap) {
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

    fun control(gamepad1: Gamepad) {
        var windmill = Windmill()
        var power = 0.0
        if (gamepad1.right_trigger  > 0.15 && gamepad1.left_trigger < 0.15) {
            power = 1.0
            while (launcher.getVelocity() >= Constants.windmillThreshold) {
                windmill.run(1.0)
            }
            while (launcher.getVelocity() < Constants.windmillThreshold) {
                windmill.run(0.0)
            }
        } else if (gamepad1.left_trigger > 0.15 && gamepad1.right_trigger < 0.15) {
            power = -1.0
            while (launcher.getVelocity() <= -Constants.windmillThreshold) {
                windmill.run(-1.0)
            }
            while (launcher.getVelocity() > -Constants.windmillThreshold) {
                windmill.run(0.0)
            }
        } else {
            power = 0.0
            windmill.run(0.0)
        }
        setPowerInVelocity(power)
    }

    fun telem(telemetry: Telemetry) {
        var launcherRPM: Double = (launcher.getVelocity() / 28 * 60)

        telemetry.addData("LauncherPower: ", launcher.getPower())
        telemetry.addData("LauncherRPM: ", launcherRPM)
    }
    }
