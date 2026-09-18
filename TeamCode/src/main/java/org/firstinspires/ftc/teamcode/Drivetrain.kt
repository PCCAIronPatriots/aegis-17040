package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.Telemetry
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.Gamepad


class Drivetrain {
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
         fr.direction = DcMotorSimple.Direction.REVERSE
         rl.direction = DcMotorSimple.Direction.REVERSE
         rr.direction = DcMotorSimple.Direction.REVERSE

     }

    fun  run(gamepad1 : Gamepad) {
        var lStickY: Float = gamepad1.left_stick_y
        var lStickX: Float = gamepad1.left_stick_x
        var rStickX: Float = gamepad1.right_stick_x

        fl.setPower((lStickY + lStickX - rStickX).toDouble()); // -1.0 - 1.0 power level (sent from Joysticks)
        rl.setPower((-lStickX - rStickX - rStickX).toDouble());
        fr.setPower((lStickX - rStickX - rStickX).toDouble());
        rr.setPower((-lStickX + rStickX - rStickX).toDouble());
    }
    fun telem(telemetry: Telemetry) {
        telemetry.addData("FrontLeftPower: ", fl.getPower())
        telemetry.addData("FrontRightPower: ", fr.getPower())
        telemetry.addData("RearLeftPower: ", rl.getPower())
        telemetry.addData("RearRightPower: ", rr.getPower())
    }
}