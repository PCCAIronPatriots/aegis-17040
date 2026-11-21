package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.Telemetry
import com.qualcomm.robotcore.hardware.Gamepad
import com.qualcomm.robotcore.hardware.CRServo


class Intake {
    private lateinit var intakeMotor: DcMotor

    //private lateinit var lIS: CRServo //lIS stands for left intake servo
    private lateinit var rIS: CRServo //rIS is the same

    /**
     * Initializes the intake hardware components and sets their motor/servo directions.
     */
    fun init(hardwareMap: HardwareMap) {
        intakeMotor = hardwareMap.get(DcMotor::class.java, "intakeMotor")
        //lIS = hardwareMap.get(CRServo::class.java, "lIS")
        rIS = hardwareMap.get(CRServo::class.java, "rIS")

        intakeMotor.direction = DcMotorSimple.Direction.FORWARD
        //lIS.direction = DcMotorSimple.Direction.REVERSE
        rIS.direction = DcMotorSimple.Direction.FORWARD
    }

    /**
     * Controls intake motor and servo power based on gamepad bumper inputs.
     */
    fun run(gamepad1: Gamepad) {
        fun intakePower(power: Double) {
            intakeMotor.power = (power)
            //lIS.power = (Constants.intakeLServo * power)
            rIS.power = (power)
        }

        var intakePower = 0.0


        intakePower = if (gamepad1.left_bumper && !gamepad1.right_bumper) {
            -1.0
        } else if (gamepad1.right_bumper && !gamepad1.left_bumper) {
            1.0
        } else {
            0.0
        }
        intakePower(intakePower)
    }

    /**
     * Outputs current intake telemetry data.
     */
    fun telem(telemetry: Telemetry) {
        telemetry.addData("IntakeMotorPower:", intakeMotor.power)
    }

    fun start(hardwareMap: HardwareMap, telemetry: Telemetry) {
        init(hardwareMap)
        telem(telemetry)
    }

    fun loop(gamepad1: Gamepad, telemetry: Telemetry) {
        run(gamepad1)
        telem(telemetry)
    }
}

