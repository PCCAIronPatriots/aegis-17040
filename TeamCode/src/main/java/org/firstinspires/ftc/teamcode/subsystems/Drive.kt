package org.firstinspires.ftc.teamcode.subsystems

import com.pedropathing.follower.Follower
import com.pedropathing.follower.ManualDrive
import com.pedropathing.math.Pose
import com.qualcomm.robotcore.hardware.Gamepad
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.pedro.Constants

class Drive {
    private lateinit var follower: Follower


    fun init(hardwareMap: HardwareMap) {
        follower = Constants.create(hardwareMap)
        follower.setPose(Pose(0.0, 0.0, 0.0))
    }

    fun run(gamepad1: Gamepad) {
        ManualDrive.driveOrHold(
            follower,
            -gamepad1.left_stick_y.toDouble() * org.firstinspires.ftc.teamcode.Constants.ChassisSpeed,
            -gamepad1.left_stick_x.toDouble() * org.firstinspires.ftc.teamcode.Constants.ChassisSpeed,
            -gamepad1.right_stick_x.toDouble() * org.firstinspires.ftc.teamcode.Constants.ChassisSpeed,
        )
        follower.update()
    }

    fun telemetry(telemetry: Telemetry) {
        telemetry.addData("X", follower.pose().x())
        telemetry.addData("Y", follower.pose().y())
        telemetry.addData("Heading", Math.toDegrees(follower.pose().heading()))
        telemetry.addData("Follower Mode", follower.mode())
    }

    fun start(hardwareMap: HardwareMap, telemetry: Telemetry) {
        init(hardwareMap)
        telemetry(telemetry)

    }

    fun loop(gamepad1: Gamepad, telemetry: Telemetry) {
        run(gamepad1)
        telemetry(telemetry)
    }
}