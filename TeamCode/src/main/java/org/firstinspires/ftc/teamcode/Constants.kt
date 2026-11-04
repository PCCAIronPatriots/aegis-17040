package org.firstinspires.ftc.teamcode
import com.qualcomm.robotcore.hardware.Gamepad



object Constants {
    const val intakeMotor = 1.0
    const val intakeLServo = 1.0
    const val intakeRServo = 1.0
    const val windmillServo = 1.0
    const val chassisSpeed = 0.8

    const val launcherSetSpeed = 0.47
    const val launcherSetVelocity = launcherSetSpeed * 2800
    const val windmillThresholdValue = 4 // - value in % ex. 5(%)
    const val launcherTickPerRevolution = 28.0 //motor tick/revololution (5203 6000rpm gobuilda motor)
}