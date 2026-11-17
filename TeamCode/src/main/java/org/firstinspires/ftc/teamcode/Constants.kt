package org.firstinspires.ftc.teamcode
import com.qualcomm.robotcore.hardware.Gamepad



object Constants {
    const val JoystickDeadZone = 0.05
    const val TriggerDeadZone = 0.05
    const val IntakeMotor = 1.0
    const val IntakeLServo = 1.0
    const val IntakeRServo = 1.0
    const val WindmillServo = 1.0
    const val ChassisSpeed = 0.8

    const val LauncherSetSpeed = 0.455
    const val LauncherSetVelocity = LauncherSetSpeed * 2800
    const val WindmillThresholdValue = 4 // - value in % ex. 5(%)
    const val LauncherTickPerRevolution = 28.0 //motor tick/revololution (5203 6000rpm gobilda motor)
}