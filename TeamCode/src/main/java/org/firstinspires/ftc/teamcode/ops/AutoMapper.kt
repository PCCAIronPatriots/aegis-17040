package org.firstinspires.ftc.teamcode.ops
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.pedropathing.follower.Follower
import com.pedropathing.follower.ManualDrive
import com.pedropathing.math.Pose
import org.firstinspires.ftc.teamcode.pedro.Constants
import java.io.File

@TeleOp (name = "AutoMapper", group = "OpMode")
class AutoMapper : OpMode() {
    private lateinit var follower: Follower
    private var driveOrHoldEnabled = true
    private var lastX = false
    private val storedPoses = mutableListOf<Pose>()
    private var lastA = false
    private var lastY = false
    private var writeFile = false

    /**
     * Initializes the follower instance and sets initial pose to (0, 0, 0).
     */
    override fun init() {
        follower = Constants.create(hardwareMap)
        follower.setPose(Pose(0.0, 0.0, 0.0))
    }

    /**
     * Main control loop handling toggle buttons, pose storage, driving, and telemetry output.
     */
    override fun loop() {
        val currentX = gamepad1.x // robot control toggle switch
        if (currentX && !lastX) {
            driveOrHoldEnabled = !driveOrHoldEnabled
        }
        lastX = currentX

        val currentY = gamepad1.y  // writeFile toggle switch
        if (currentY && !lastY) {
            writeFile = !writeFile
        }
        lastY = currentY

        val currentA = gamepad1.a // add pose
        if (currentA && !lastA) {
            if (storedPoses.size >= 10) {
                storedPoses.removeAt(0)
            }
            storedPoses.add(follower.pose())
        }
        lastA = currentA

        if (driveOrHoldEnabled) { // robot control
            ManualDrive.driveOrHold(
                follower,
                -gamepad1.left_stick_y.toDouble() * 0.5,
                -gamepad1.left_stick_x.toDouble() * 0.5,
                -gamepad1.right_stick_x.toDouble() * 0.5,
            )
        }

        if (!driveOrHoldEnabled) {
            if (gamepad1.b) { // zero robot out
                follower.setPose(Pose(0.0, 0.0, 0.0))
            }
        }
        follower.update()
        telemetry.addLine("Add Pose: A")
        telemetry.addLine("Enable Driving: X")
        telemetry.addLine("Zero Robot on Field: B")
        telemetry.addLine("Enable Pose Storing: Y")
        telemetry.addLine()
        telemetry.addData("DriveOrHold: ", driveOrHoldEnabled)
        telemetry.addData("WriteFile :", writeFile)
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

    /**
     * Writes recorded poses to a text file upon stopping if file writing is enabled.
     */
    override fun stop() { // write file if enabled after driver clicks stop
        val file = File("storedPoses.txt")
        if (writeFile) {
            file.bufferedWriter().use { writer ->
                for ((index, pose) in storedPoses.withIndex()) {
                    writer.write(
                        "Pose $index, X: %.2f, Y: %.2f, H: %.1f".format(
                            pose.x(), pose.y(), Math.toDegrees(pose.heading())
                        )
                    )
                    writer.newLine()
                }
            }
        }
    }
}