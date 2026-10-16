package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.Telemetry
import com.qualcomm.robotcore.hardware.Gamepad


class MecanumDrive {
    private lateinit var fl: DcMotor
    private lateinit var fr: DcMotor
    private lateinit var rl: DcMotor
    private lateinit var rr: DcMotor
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

    fun  run(gamepad1 : Gamepad) {
        val lStickY: Float = gamepad1.left_stick_y
        val lStickX: Float = -gamepad1.left_stick_x
        val rStickX: Float = gamepad1.right_stick_x

        fl.setPower((-lStickY + lStickX + rStickX).toDouble() * Constants.chassisSpeed) // -1.0 - 1.0 power level (sent from Joysticks)
        rl.setPower((-lStickY - lStickX + rStickX).toDouble() * Constants.chassisSpeed)
        fr.setPower((-lStickY - lStickX - rStickX).toDouble() * Constants.chassisSpeed)
        rr.setPower((-lStickY + lStickX - rStickX).toDouble() * Constants.chassisSpeed)

        /*if (mecanum == true) {
            fl.setPower((-lStickY + lStickX + rStickX).toDouble()) // -1.0 - 1.0 power level (sent from Joysticks)
            rl.setPower((-lStickY - lStickX + rStickX).toDouble())
            fr.setPower((-lStickY - lStickX - rStickX).toDouble())
            rr.setPower((-lStickY + lStickX - rStickX).toDouble())
        } else run {
            val yAxis = -gamepad1.left_stick_y
            val xAxis = gamepad1.right_stick_x

            fl.setPower((yAxis + xAxis).toDouble())
            fr.setPower((yAxis - xAxis).toDouble())
            rl.setPower((yAxis + xAxis).toDouble())
            rr.setPower((yAxis - xAxis).toDouble())
            */

        }

    fun telem(telemetry: Telemetry) {
        telemetry.addData("FrontLeftPower: ", fl.getPower())
        telemetry.addData("FrontRightPower: ", fr.getPower())
        telemetry.addData("RearLeftPower: ", rl.getPower())
        telemetry.addData("RearRightPower: ", rr.getPower())
    }
}