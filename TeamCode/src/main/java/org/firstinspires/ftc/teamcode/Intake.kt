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
        rIS = hardwareMap.get(CRServo::class.java,"rIS")

        intakeMotor.direction = DcMotorSimple.Direction.REVERSE
        lIS.direction = DcMotorSimple.Direction.REVERSE
        rIS.direction = DcMotorSimple.Direction.FORWARD
    }

    fun run(gamepad1: Gamepad) {
        val rBumper = gamepad1.right_bumper

        if (rBumper) {
            intakeMotor.setPower(Constants.intakeMotor)
            lIS.setPower(Constants.intakeLServo)
            rIS.setPower(Constants.intakeRServo)
        }
    }

    fun telem(telemetry: Telemetry) {
        telemetry.addData("IntakeMotorPower:" , intakeMotor.getPower() )


    }
}