package org.firstinspires.ftc.teamcode
import com.qualcomm.robotcore.hardware.Gamepad



object Constants {
    const val intakeMotor = 1.0
    const val intakeLServo = 1.0
    const val intakeRServo = 1.0
    const val windmillServo = 1.0
    const val chassisSpeed = 0.8

    const val launcherSetSpeed = 0.45
    const val launcherSetVelocity = launcherSetSpeed * 2800
    const val windmillThreshold = launcherSetVelocity - (launcherSetVelocity * .5) // 5% threshold
    const val launcherTickPerRevolution = 28.0 //motor tick/revololution (5203 6000rpm gobuilda motor)
}