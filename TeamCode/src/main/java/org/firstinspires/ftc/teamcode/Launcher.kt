package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.Gamepad
import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.PIDFCoefficients
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.Constants.LauncherSetVelocity
import kotlin.time.TimeSource
import kotlin.time.Duration.Companion.seconds


class Launcher(private val windmill: Windmill = Windmill()) {
    private lateinit var launcher: DcMotorEx
    val tDZ = Constants.TriggerDeadZone

    fun getCurV(): Double = launcher.velocity


    fun init(hardware: HardwareMap) {
        windmill.init(hardware)
        launcher = hardware.get(DcMotorEx::class.java, "launcher")
        launcher.direction = DcMotorSimple.Direction.FORWARD
        launcher.setPIDFCoefficients(
            DcMotor.RunMode.RUN_USING_ENCODER,
            PIDFCoefficients(40.0, 0.0, 0.0, 12.5)
        )
        launcher.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
    }

    fun setVInP(power: Double) { // set launcher velocity in percentage of power
        launcher.velocity = (Constants.LauncherTickPerRevolution * 100 * power * Constants.LauncherSetSpeed)
    }

    fun control(gamepad1: Gamepad) { //gamepad control
        windmill.autoRun(getCurV())
        val power = if (gamepad1.right_trigger > tDZ && gamepad1.left_trigger < tDZ) {
            1.0
        } else if (gamepad1.left_trigger > tDZ && gamepad1.right_trigger < tDZ) {
            -1.0
        } else {
            0.0
        }
        setVInP(power)
    }

    fun shoot() {
        setVInP(1.0)
        windmill.autoRun(getCurV())
    }

    fun stop() {
        setVInP(0.0)
        windmill.run(0.0)
    }

    fun telem(telemetry: Telemetry) {
        val launcherRPM: Double = (launcher.velocity / 28 * 60)
        val launcherSetRPM: Double = (Constants.LauncherSetSpeed / 28 * 60)

        telemetry.addData("LauncherPower: ", launcher.power)
        telemetry.addData("LauncherRPM: ", launcherRPM)
        telemetry.addData("LauncherSetRPM: ", launcherSetRPM)
    }
    fun rTrigStat(gamepad1: Gamepad) : Boolean {
        if (gamepad1.right_trigger > tDZ) {
            return true
        } else return false
    }
    fun lTrigStat(gamepad1: Gamepad) : Boolean {
        if (gamepad1.left_trigger > tDZ) {
            return true
        } else return false
    }
}

