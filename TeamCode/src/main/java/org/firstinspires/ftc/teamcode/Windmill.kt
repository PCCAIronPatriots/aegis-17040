package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap
//import com.qualcomm.robotcore.hardware.Gamepad
import com.qualcomm.robotcore.hardware.CRServo
import org.firstinspires.ftc.teamcode.Constants.LAUNCHER_SET_VELOCITY

class Windmill {
    private lateinit var windmill: CRServo

    /**
     * Sets raw power to the windmill servo.
     */
    fun run(power: Double) {
        windmill.power = power
    }

    /**
     * Initializes the windmill CRServo hardware.
     */
    fun init(hardware: HardwareMap) {
        windmill = hardware.get(CRServo::class.java, "windmill")
        windmill.direction = DcMotorSimple.Direction.FORWARD

    }
    /**
     * Provides manual control over the windmill servo using gamepad buttons A and B.
     */
    /*fun manualControl(gamepad1: Gamepad) {
        windmill.power = 0.0
        if (gamepad1.a) {
            windmill.power = (1.0)
        } else if (gamepad1.b) {
            windmill.power = (-1.0)
        } else {
            windmill.power = 0.0
        }
    }
    */

    /**
     * Automatically operates the windmill based on launcher velocity thresholds.
     */
    fun autoRun(currentVelocity: Double) {
        val windmillThreshold = (LAUNCHER_SET_VELOCITY * (Constants.WindmillThresholdValue / 100.0))
        if (currentVelocity > windmillThreshold) {
            run(1.0)
        } else if (currentVelocity <= -windmillThreshold) {
            run(-1.0)
        } else {
            run(0.0)
        }
    }
}

