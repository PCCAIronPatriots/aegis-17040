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

    /**
     * Initializes the Limelight camera and sets up polling rate and robot orientation tracking.
     */
    fun init(hardware : HardwareMap) {
        lime = hardware.get(Limelight3A::class.java, "limelight")
        lime.setPollRateHz(100)
        lime.start()
        imu = Constants.create(hardware)
        lime.updateRobotOrientation(yaw)

    }

    /**
     * Checks whether the latest Limelight result is valid and present.
     */
    fun valid() : Boolean {
        if (result != null && result.isValid()) {
            return true
        }
        return false
    }

    /**
     * Retrieves the robot's localization coordinates (X, Y) from MT2 botpose.
     */
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

    /**
     * Placeholder function for future distance calculation logic.
     */
    fun getDistance() {
        //smth should be here (HELP ME)
    }

    /**
     * Outputs Limelight targeting data via telemetry.
     */
    fun telemetry(telemetry: Telemetry) {
        telemetry.addData("Target X", tx)
        telemetry.addData("Target Y", ty)
        telemetry.addData("Target Area", ta)

        telemetry.addData("Limelight", "No Targets")
    }
}