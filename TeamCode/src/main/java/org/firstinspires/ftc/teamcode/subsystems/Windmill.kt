package org.firstinspires.ftc.teamcode.subsystems

import com.qualcomm.robotcore.hardware.CRServo
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.teamcode.Constants

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
     * Automatically operates the windmill based on launcher velocity thresholds.
     */
    fun autoRun(currentVelocity: Double) {
        val windmillThreshold = (Constants.LAUNCHER_SET_VELOCITY * (Constants.WindmillThresholdValue / 100.0))
        if (currentVelocity > windmillThreshold) {
            run(1.0)
        } else if (currentVelocity <= -windmillThreshold) {
            run(-1.0)
        } else {
            run(0.0)
        }
    }
}