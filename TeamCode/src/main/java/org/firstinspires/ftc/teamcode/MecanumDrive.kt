package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.Telemetry
import com.qualcomm.robotcore.hardware.Gamepad
import kotlin.math.abs

class MecanumDrive {
    private lateinit var fl: DcMotor
    private lateinit var fr: DcMotor
    private lateinit var rl: DcMotor
    private lateinit var rr: DcMotor

    /**
     * Initializes the four mecanum drive motors with brake behavior and motor directions.
     */
     fun init(hardwareMap: HardwareMap) {
         fl = hardwareMap.get(DcMotor::class.java, "fl")
         fr = hardwareMap.get(DcMotor::class.java, "fr")
         rl = hardwareMap.get(DcMotor::class.java, "rl")
         rr = hardwareMap.get(DcMotor::class.java, "rr")

         fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE)
         fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE)
         rl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE)
         rr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE)

         fl.direction = DcMotorSimple.Direction.REVERSE
         fr.direction = DcMotorSimple.Direction.FORWARD
         rl.direction = DcMotorSimple.Direction.REVERSE
         rr.direction = DcMotorSimple.Direction.REVERSE
     }

    /**
     * Calculates and applies motor powers for mecanum driving based on gamepad joystick inputs.
     */
    fun  run(gamepad1 : Gamepad) {
        val lStickY: Float = gamepad1.left_stick_y
        val lStickX: Float = gamepad1.left_stick_x
        val rStickX: Float = gamepad1.right_stick_x

        fl.setPower((-lStickY + lStickX + rStickX).toDouble() * Constants.ChassisSpeed) // -1.0 - 1.0 power level (sent from Joysticks)
        rl.setPower((-lStickY - lStickX + rStickX).toDouble() * Constants.ChassisSpeed)
        fr.setPower((-lStickY - lStickX - rStickX).toDouble() * Constants.ChassisSpeed)
        rr.setPower((-lStickY + lStickX - rStickX).toDouble() * Constants.ChassisSpeed)


        }

    /**
     * Reports individual drive motor power levels via telemetry.
     */
    fun telem(telemetry: Telemetry) {
        telemetry.addData("FrontLeftPower: ", fl.getPower())
        telemetry.addData("FrontRightPower: ", fr.getPower())
        telemetry.addData("RearLeftPower: ", rl.getPower())
        telemetry.addData("RearRightPower: ", rr.getPower())
    }

    /**
     * Checks if joystick input levels exceed the deadzone threshold.
     */
    fun getJoyStat(gamepad1: Gamepad) : Boolean {
        val drive = -gamepad1.left_stick_y.toDouble()
        val strafe = -gamepad1.left_stick_x.toDouble()
        val turn = -gamepad1.right_stick_x.toDouble()
        val joyDZ = Constants.JoystickDeadZone
        val joystickActive = abs(drive) > joyDZ ||
            abs(strafe) > joyDZ ||
            abs(turn) > joyDZ
        return joystickActive
    }
}