package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.Telemetry
import com.qualcomm.robotcore.hardware.Gamepad
import com.qualcomm.robotcore.hardware.CRServo


class Intake {
    private lateinit var intakeMotor: DcMotor
    private lateinit var lIS: CRServo //lIS stands for left intake servo
    private lateinit var rIS: CRServo //rIS is the same

    fun init(hardwareMap: HardwareMap) {
        intakeMotor = hardwareMap.get(DcMotor::class.java, "intakeMotor")
        lIS = hardwareMap.get(CRServo::class.java, "lIS")
        rIS = hardwareMap.get(CRServo::class.java, "rIS")

        intakeMotor.direction = DcMotorSimple.Direction.FORWARD
        lIS.direction = DcMotorSimple.Direction.REVERSE
        rIS.direction = DcMotorSimple.Direction.FORWARD
    }

    fun run(gamepad1: Gamepad) {

        val rBump: Boolean = gamepad1.right_bumper
        val lBump: Boolean = gamepad1.left_bumper

        fun intakePower(power: Double) {
            intakeMotor.setPower(Constants.intakeMotor * power)
            lIS.setPower(Constants.intakeLServo * power)
            rIS.setPower(Constants.intakeRServo * power)
        }

        var intakePower: Double = 0.0


        if (rBump && (intakePower == 0.0)) {
            intakePower(1.0)
        } else if (rBump && (intakePower == 1.0)) {
            intakePower(0.0)
            if (lBump && ((intakePower == 0.0)) || (intakePower == 1.0)) {
                intakePower = -1.0
            } else if (lBump && (intakePower == -1.0)) {
                intakePower = 0.0
            }

            intakePower(intakePower)


        }

        fun telem(telemetry: Telemetry) {
            telemetry.addData("IntakeRealPower:", intakeMotor.getPower())
            telemetry.addData("IntakeSetPower: ", intakePower)
        }
    }
}
