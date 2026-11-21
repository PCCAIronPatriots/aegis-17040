package org.firstinspires.ftc.teamcode

import com.acmerobotics.dashboard.config.Config

@Config
object Constants {
    @JvmField var JoystickDeadZone = 0.05
    @JvmField var TriggerDeadZone = 0.05
    //const val IntakeLServo = 1.0
    @JvmField var ChassisSpeed = 0.8
    @JvmField var LauncherSetSpeed = 0.455
    val LAUNCHER_SET_VELOCITY = LauncherSetSpeed * 2800
    @JvmField var WindmillThresholdValue = 4 // - value in % ex. 5(%)
    const val LAUNCHER_TICK_PER_REVOLUTION = 28.0 //motor tick/revololution (5203 6000rpm gobilda motor)
}