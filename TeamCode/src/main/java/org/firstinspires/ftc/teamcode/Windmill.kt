package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.Telemetry
import com.qualcomm.robotcore.hardware.Gamepad
import com.qualcomm.robotcore.hardware.CRServo
import com.qualcomm.robotcore.hardware.DcMotorEx

class Windmill {
    private lateinit var windmill: CRServo

    fun init(hardwaremap: HardwareMap) {
        windmill = hardwaremap.get(CRServo::class.java, "windmill")
        windmill.direction = DcMotorSimple.Direction.FORWARD

    }
    fun control(gamepad1: Gamepad) {
        windmill.power = 0.0
        if (gamepad1.a) {
            windmill.power = (Constants.windmillServo)
        } else if (gamepad1.b) {
            windmill.power = (-Constants.windmillServo)
        } else {
            windmill.power = 0.0
        }
    }

    fun run(power: Double) {
        windmill.power = power
    }

    fun telem(telemetry: Telemetry) {
        telemetry.addData("WindmillServoPowa: ", windmill.power)
    }

}