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
        /*lIS = hardwareMap.get(CRServo::class.java, "lIS")
        rIS = hardwareMap.get(CRServo::class.java, "rIS") */

        intakeMotor.direction = DcMotorSimple.Direction.FORWARD
        /* lIS.direction = DcMotorSimple.Direction.REVERSE
        rIS.direction = DcMotorSimple.Direction.FORWARD */
    }

    fun run(gamepad1: Gamepad) {
        fun intakePower(power: Double) {
            intakeMotor.power = (Constants.intakeMotor * power)
            /* lIS.power = (Constants.intakeLServo * power)
            rIS.power = (Constants.intakeRServo * power) */
        }
        var intakePower: Double = 0.0


        if (gamepad1.left_bumper && !gamepad1.right_bumper) {
            intakePower = -1.0
        } else if (gamepad1.right_bumper && !gamepad1.left_bumper) {
            intakePower = 1.0
        } else {
            intakePower = 0.0
        }
            intakePower(intakePower)
        }


        fun telem(telemetry: Telemetry) {
            telemetry.addData("IntakeRealPower:", intakeMotor.getPower())
        }
    }

