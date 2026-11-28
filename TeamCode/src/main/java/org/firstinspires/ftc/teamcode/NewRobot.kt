package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.Gamepad
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.subsystems.limelight.LimeLight
import org.firstinspires.ftc.teamcode.subsystems.Turret
import org.firstinspires.ftc.teamcode.subsystems.Drive
import org.firstinspires.ftc.teamcode.subsystems.Intake
import org.firstinspires.ftc.teamcode.subsystems.Launcher

class NewRobot(val team: Team?) {
    var launch = Launcher() // this includes the windmill
    var intake = Intake()
    var drive = Drive()
    var lime = LimeLight()
    var turret =  Turret()

    fun init(hardwareMap: HardwareMap, telemetry: Telemetry) {
        drive.start(hardwareMap, telemetry)
        intake.start(hardwareMap, telemetry)
        launch.start(hardwareMap, telemetry)
        lime.start(hardwareMap, telemetry)
        turret.start(hardwareMap)
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
        lime.loop(team, telemetry)
        turret.loop(lime.getBestTarget(team)!!.averageTx, lime.getResult().valid)
        telemetry.update()
    }

    fun stop() {
        lime.stop()
    }
}