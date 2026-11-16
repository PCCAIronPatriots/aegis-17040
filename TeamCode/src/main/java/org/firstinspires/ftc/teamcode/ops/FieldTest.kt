package org.firstinspires.ftc.teamcode.ops
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.pedropathing.follower.Follower
import com.pedropathing.follower.ManualDrive
import com.pedropathing.math.Pose
import org.firstinspires.ftc.teamcode.pedro.Constants

@TeleOp (name = "FieldTest", group = "OpMode")
class FieldTest : OpMode() {
    private lateinit var follower: Follower
    private var driveOrHoldEnabled = true
    private var lastX = false
    private val storedPoses = mutableListOf<Pose>()
    private var lastA = false

    override fun init() {
        follower = Constants.create(hardwareMap)
        follower.setPose(Pose(0.0, 0.0, 0.0))
    }

    override fun start() {
        telemetry.addData("DriveOrHold: ", driveOrHoldEnabled)
        telemetry.addData("Stored Poses: ", storedPoses.size)
        for ((index, pose) in storedPoses.withIndex()) {
            telemetry.addData("Pose $index", "X: %.2f, Y: %.2f, H: %.1f°",
                pose.x(), pose.y(), Math.toDegrees(pose.heading()))
        }
        telemetry.addData("Active X: ", follower.pose().x())
        telemetry.addData("Active Y: ", follower.pose().y())
        telemetry.addData("ActiveHeading: ", Math.toDegrees(follower.pose().heading()))
        telemetry.update()
    }

    override fun loop() {
        val currentX = gamepad1.x
        if (currentX && !lastX) {
            driveOrHoldEnabled = false
        }
        lastX = currentX

        val currentA = gamepad1.a
        if (currentA && !lastA) {
            if (storedPoses.size >= 10) {
                storedPoses.removeAt(0)
            }
            storedPoses.add(follower.pose())
        }
        lastA = currentA

        if (driveOrHoldEnabled) {
            ManualDrive.driveOrHold(
                follower,
                -gamepad1.left_stick_y.toDouble() * 0.5,
                -gamepad1.left_stick_x.toDouble() * 0.5,
                -gamepad1.right_stick_x.toDouble() * 0.5,
            )
        }

        if (gamepad1.b) {
            follower.setPose(Pose(0.0, 0.0, 0.0))
        }
        follower.update()
        telemetry.addData("DriveOrHold: ", driveOrHoldEnabled)
        telemetry.addData("Stored Count: ", storedPoses.size)
        for ((index, pose) in storedPoses.withIndex()) {
            telemetry.addData("Pose $index", "X: %.2f, Y: %.2f, H: %.1f°",
                pose.x(), pose.y(), Math.toDegrees(pose.heading()))
        }
        telemetry.addData("Active X: ", follower.pose().x())
        telemetry.addData("Active Y: ", follower.pose().y())
        telemetry.addData("ActiveHeading: ", Math.toDegrees(follower.pose().heading()))
        telemetry.update()
    }
}