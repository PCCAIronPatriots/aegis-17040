package org.firstinspires.ftc.teamcode

import com.acmerobotics.dashboard.config.Config

@Config
object Constants {
    @JvmField var JoystickDeadZone = 0.05
    @JvmField var TriggerDeadZone = 0.05
    @JvmField var IntakeMotor = 1.0
    //const val IntakeLServo = 1.0
    @JvmField var ChassisSpeed = 0.8
    @JvmField var LauncherSetSpeed = 0.455
    @JvmField val LauncherSetVelocity = LauncherSetSpeed * 2800
    @JvmField var WindmillThresholdValue = 4 // - value in % ex. 5(%)
    @JvmField val LauncherTickPerRevolution = 28.0 //motor tick/revololution (5203 6000rpm gobilda motor)
}