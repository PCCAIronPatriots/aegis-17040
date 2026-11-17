package org.firstinspires.ftc.teamcode

import com.qualcomm.hardware.limelightvision.LLResult
import com.qualcomm.hardware.limelightvision.Limelight3A
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.Gamepad
import org.firstinspires.ftc.robotcore.external.Telemetry
import com.pedropathing.follower.Follower
import com.pedropathing.math.Pose
import com.qualcomm.hardware.limelightvision.LLResultTypes
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D
import org.firstinspires.ftc.teamcode.pedro.Constants
import java.util.Collections.list


class LimeLight {
    lateinit var lime : Limelight3A
    lateinit var imu: Follower
    val result : LLResult = lime.getLatestResult()

    val tx = result.tx // How far left or right the target is (degrees)
    val ty = result.ty // How far up or down the target is (degrees)
    val ta = result.ta // How big the target looks (0%-100% of the image)
    val yaw = imu.pose().heading()

    fun init(hardware : HardwareMap) {
        lime = hardware.get(Limelight3A::class.java, "limelight")
        lime.setPollRateHz(100)
        lime.start()
        imu = Constants.create(hardware)
        lime.updateRobotOrientation(yaw)

    }
    fun valid() : Boolean {
        if (result != null && result.isValid()) {
            return true
        }
        return false
    }

    fun getLimeLoco() : Pair<Double, Double>? {
        if (valid()) {
            val botPoseMt2: Pose3D = result.botpose_MT2
            if (botPoseMt2 != null) {
                val x: Double = botPoseMt2.position.x
                val y: Double = botPoseMt2.position.y
                return Pair(x, y)
            }
        }
        return null
    }

    fun getDistance() {
        //smth should be here (HELP ME)
    }

    fun telemetry(telemetry: Telemetry) {
        telemetry.addData("Target X", tx)
        telemetry.addData("Target Y", ty)
        telemetry.addData("Target Area", ta)

        telemetry.addData("Limelight", "No Targets")
    }
}