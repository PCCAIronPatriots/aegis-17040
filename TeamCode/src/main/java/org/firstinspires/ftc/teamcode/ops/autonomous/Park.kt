package org.firstinspires.ftc.teamcode.ops.autonomous

import com.pedropathing.api.Paths
import com.pedropathing.api.PoseFactory
import com.pedropathing.follower.Follower
import com.pedropathing.ivy.Scheduler
import com.pedropathing.ivy.pedro.PedroCommands
import com.pedropathing.math.Pose
import com.pedropathing.paths.Path
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import org.firstinspires.ftc.teamcode.pedro.Constants

@Autonomous(name = "Park", group = "Auto")
class Park : OpMode() {

    private lateinit var follower: Follower

    private val p: PoseFactory = PoseFactory.degrees()

    // Start position at x = 24.0, y = 24.0, heading = 0.0
    private val startPose: Pose = p.of(24.0, 24.0, 0.0)

    // Moved forward by 12 inches (1 foot) on the X-axis: x = 36.0, keeping y and heading the same
    private val parkPose: Pose = p.of(24.0, 36.0, 0.0)

    /**
     * Defines the linear parking path from startPose to parkPose.
     */
    private fun park(): Path {
        return Paths.line(startPose, parkPose).linear(startPose, parkPose)
    }

    /**
     * Resets the scheduler, creates the follower, and sets the starting pose.
     */
    override fun init() {
        Scheduler.reset()
        follower = Constants.create(hardwareMap)
        follower.setPose(startPose)
    }

    /**
     * Schedules the path following action when autonomous starts.
     */
    override fun start() {
        Scheduler.schedule(PedroCommands.follow(follower, park()))
    }

    /**
     * Updates follower and scheduler execution, and logs position telemetry.
     */
    override fun loop() {
        follower.update()
        Scheduler.execute()

        telemetry.addData("X", follower.pose().x())
        telemetry.addData("Y", follower.pose().y())
        telemetry.addData("Heading", Math.toDegrees(follower.pose().heading()))
        telemetry.addData("Follower Mode", follower.mode())
        telemetry.update()
    }
}