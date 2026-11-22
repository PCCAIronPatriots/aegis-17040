package org.firstinspires.ftc.teamcode.ops.Auto

import com.pedropathing.api.Paths
import com.pedropathing.api.PoseFactory
import com.pedropathing.follower.Follower
import com.pedropathing.ivy.Scheduler
import com.pedropathing.ivy.pedro.PedroCommands
import com.pedropathing.math.Pose
import com.pedropathing.paths.Path
import com.pedropathing.paths.curves.bezier.BezierCurve
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.mechanisms.Launcher
import org.firstinspires.ftc.teamcode.pedro.Constants

@Autonomous(name = "Score", group = "Auto")
class Score : OpMode() {
    enum class State {
        LAUNCHING,
        DRIVING
    }

    private var state = State.LAUNCHING
    private val timer = ElapsedTime()

    var launch = Launcher()

    private lateinit var follower: Follower

    private val p: PoseFactory = PoseFactory.degrees()

    // Start position at x = 24.0, y = 24.0, heading = 0.0
    private val startPose: Pose = p.of(124.15, 49.25, 0.0)
    private val controlPose : Pose = p.of(95.0, 10.0, 0.0)

    // Moved forward by 12 inches (1 foot) on the X-axis: x = 36.0, keeping y and heading the same
    private val parkPose: Pose = p.of(39.1, 2.5, 0.0)

    /**
     * Defines the scoring/parking bezier curve path.
     */
    private fun park(): Path {
        return Paths.path(BezierCurve(startPose, controlPose, parkPose)).linear(startPose, parkPose)
    }

    /**
     * Resets scheduler, initializes follower pose, and initializes launcher telemetry.
     */
    override fun init() {
        Scheduler.reset()
        follower = Constants.create(hardwareMap)
        follower.setPose(startPose)

        launch.init(hardwareMap)
        launch.telem(telemetry)
    }

    /**
     * Resets timer and sets initial state to LAUNCHING when autonomous starts.
     */
    override fun start() {
        timer.reset()
        state = State.LAUNCHING
    }

    /**
     * State machine loop handling launching phase and driving phase.
     */
    override fun loop() {
        when (state) {
            State.LAUNCHING -> {
                launch.shoot()
                if (timer.seconds() >= 15.0) {
                    launch.stop()
                    Scheduler.schedule(PedroCommands.follow(follower, park()))
                    state = State.DRIVING
                }
            }
            State.DRIVING -> {
                follower.update()
                Scheduler.execute()
            }
        }

        telemetry.addData("State", state)
        telemetry.addData("X", follower.pose().x())
        telemetry.addData("Y", follower.pose().y())
        telemetry.addData("Heading", Math.toDegrees(follower.pose().heading()))
        telemetry.addData("Follower Mode", follower.mode())
        telemetry.update()
    }
}