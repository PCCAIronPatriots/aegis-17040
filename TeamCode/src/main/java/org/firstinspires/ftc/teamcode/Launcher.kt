package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.Gamepad
import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.PIDFCoefficients
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.Constants.launcherSetVelocity
import kotlin.time.TimeSource
import kotlin.time.Duration.Companion.seconds


class Launcher(private val windmill: Windmill = Windmill()) {
    private lateinit var launcher: DcMotorEx

    fun getCurV(): Double = launcher.getVelocity()


    fun init(hardwaremap: HardwareMap) {
        windmill.init(hardwaremap)
        launcher = hardwaremap.get(DcMotorEx::class.java, "launcher")
        launcher.direction = DcMotorSimple.Direction.FORWARD
        launcher.setPIDFCoefficients(
            DcMotor.RunMode.RUN_USING_ENCODER,
            PIDFCoefficients(40.0, 0.0, 0.0, 12.5)
        )
    }

    fun setVInP(power: Double) { // set launcher velocity in percentage of power
        launcher.setVelocity(Constants.launcherTickPerRevolution * 100 * power * Constants.launcherSetSpeed)
    }

     fun control(gamepad1: Gamepad) { //gamepad control
         windmill.autoRun(getCurV())
         var power = 0.0
         if (gamepad1.right_trigger > 0.15 && gamepad1.left_trigger < 0.15) {
             power = 1.0
         } else if (gamepad1.left_trigger > 0.15 && gamepad1.right_trigger < 0.15) {
             power = -1.0
         } else {
             power = 0.0
         }
         setVInP(power)
     }

    fun runAuto(timeInSeconds: Double) { //autonomous control of launcher
        val startTime = TimeSource.Monotonic.markNow()
        val runTime = timeInSeconds.seconds
        windmill.autoRun(getCurV())
        if (startTime.elapsedNow() <= runTime) {
            setVInP(1.0)
        } else {
            setVInP(0.0)
        }
    }
    fun telem(telemetry: Telemetry) {
            val launcherRPM: Double = (launcher.getVelocity() / 28 * 60)
            val launcherSetRPM: Double = (Constants.launcherSetSpeed / 28 * 60)

            telemetry.addData("LauncherPower: ", launcher.getPower())
            telemetry.addData("LauncherRPM: ", launcherRPM)
            telemetry.addData("LauncherSetRPM: ", launcherSetRPM)
        }
    }

