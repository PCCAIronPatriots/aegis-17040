package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.Telemetry
import com.qualcomm.robotcore.hardware.Gamepad
import com.qualcomm.robotcore.hardware.CRServo
import com.qualcomm.robotcore.hardware.DcMotorEx
import org.firstinspires.ftc.teamcode.Constants.LauncherSetVelocity

class Windmill {
    private lateinit var windmill: CRServo

    fun run(power: Double) {
        windmill.power = power
    }

    fun init(hardware: HardwareMap) {
        windmill = hardware.get(CRServo::class.java, "windmill")
        windmill.direction = DcMotorSimple.Direction.FORWARD

    }

    fun manualControl(gamepad1: Gamepad) {
        windmill.power = 0.0
        if (gamepad1.a) {
            windmill.power = (Constants.WindmillServo)
        } else if (gamepad1.b) {
            windmill.power = (-Constants.WindmillServo)
        } else {
            windmill.power = 0.0
        }
    }

    fun autoRun(currentVelocity: Double) {
        val windmillThreshold = (LauncherSetVelocity * (Constants.WindmillThresholdValue / 100.0))
        if (currentVelocity > windmillThreshold) {
            run(1.0)
        } else if (currentVelocity <= -windmillThreshold) {
            run(-1.0)
        } else {
            run(0.0)
        }
    }

    fun telem(telemetry: Telemetry) {
        telemetry.addData("WindmillServoPower: ", windmill.power)
    }

}