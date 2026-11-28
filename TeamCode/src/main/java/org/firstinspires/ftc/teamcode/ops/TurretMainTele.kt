package org.firstinspires.ftc.teamcode.ops


import com.qualcomm.robotcore.eventloop.opmode.OpMode
import org.firstinspires.ftc.teamcode.NewRobot
import org.firstinspires.ftc.teamcode.Team

abstract class TurretMainTele(val team : Team?) : OpMode() {
     var robot = NewRobot(team)

    override fun init() {
        robot.init(hardwareMap, telemetry)
    }

    override fun init_loop() {
        robot.initLoop()
    }

    override fun start() {
        robot.start()
    }
    override fun loop() {
        robot.loop(gamepad1, telemetry)
    }

    override fun stop() {
        robot.stop()
    }
}