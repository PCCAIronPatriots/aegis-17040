package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.Gamepad
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.subsystems.Drive
import org.firstinspires.ftc.teamcode.subsystems.Intake
import org.firstinspires.ftc.teamcode.subsystems.Launcher

class Robot(val team: Team?) {
    var launch = Launcher() // this includes the windmill
    var intake = Intake()
    var drive = Drive()

    fun init(hardwareMap: HardwareMap, telemetry: Telemetry) {
        drive.start(hardwareMap, telemetry)
        intake.start(hardwareMap, telemetry)
        launch.start(hardwareMap, telemetry)
        telemetry.update()
    }

    fun initLoop() {
        // placeholder
    }

    fun start() {
        // placeholder
    }

    fun loop(gamepad1: Gamepad, telemetry: Telemetry) {
        drive.loop(gamepad1, telemetry)
        intake.loop(gamepad1, telemetry)
        launch.loop(gamepad1, telemetry)
        telemetry.update()
    }

    fun stop() {
        // placeholder
    }
}