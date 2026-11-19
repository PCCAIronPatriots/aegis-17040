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

    /**
     * Returns the current velocity of the launcher motor.
     */
    fun getCurV(): Double = launcher.velocity


    /**
     * Configures the launcher motor and initializes the associated windmill.
     */
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

    /**
     * Sets the launcher velocity as a percentage of maximum power.
     */
    fun setVInP(power: Double) { // set launcher velocity in percentage of power
        launcher.velocity = (Constants.LauncherTickPerRevolution * 100 * power * Constants.LauncherSetSpeed)
    }

    /**
     * Controls launcher and windmill operations based on gamepad trigger inputs.
     */
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

    /**
     * Runs the launcher at full shooting speed and triggers the windmill.
     */
    fun shoot() {
        setVInP(1.0)
        windmill.autoRun(getCurV())
    }

    /**
     * Stops both the launcher and the windmill.
     */
    fun stop() {
        setVInP(0.0)
        windmill.run(0.0)
    }

    /**
     * Reports launcher power, actual RPM, and set RPM via telemetry.
     */
    fun telem(telemetry: Telemetry) {
        val launcherRPM: Double = (launcher.velocity / 28 * 60)
        val launcherSetRPM: Double = (Constants.LauncherSetSpeed / 28 * 60)

        telemetry.addData("LauncherPower: ", launcher.power)
        telemetry.addData("LauncherRPM: ", launcherRPM)
        telemetry.addData("LauncherSetRPM: ", launcherSetRPM)
    }

    /**
     * Returns true if the right trigger exceeds the deadzone threshold.
     */
    fun rTrigStat(gamepad1: Gamepad) : Boolean {
        if (gamepad1.right_trigger > tDZ) {
            return true
        } else return false
    }

    /**
     * Returns true if the left trigger exceeds the deadzone threshold.
     */
    fun lTrigStat(gamepad1: Gamepad) : Boolean {
        if (gamepad1.left_trigger > tDZ) {
            return true
        } else return false
    }
}

