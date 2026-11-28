package org.firstinspires.ftc.teamcode.ops.autonomous

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
import org.firstinspires.ftc.teamcode.subsystems.Launcher
import org.firstinspires.ftc.teamcode.pedro.Constants

@Autonomous(name = "WaitToScore", group = "Auto")
class WaitToScore : OpMode() {
    enum class State {
        LAUNCHING,
        DRIVING,
        WAITING,
    }

    private var state = State.LAUNCHING
    private val timer = ElapsedTime()

    var launch = Launcher()

    private lateinit var follower: Follower

    private val p: PoseFactory = PoseFactory.degrees()

    // Start position at x = 24.0, y = 24.0, heading = 0.0
    private val startPose: Pose = p.of(-7.75, 67.42, 180.0)
    private val controlPose : Pose = p.of(25.32, 55.63, 180.0)

    // Moved forward by 12 inches (1 foot) on the X-axis: x = 36.0, keeping y and heading the same
    private val parkPose: Pose = p.of(2.00, 20.74, 180.0)

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
        state = State.WAITING
    }

    /**
     * State machine loop handling launching phase and driving phase.
     */
    override fun loop() {
        when (state) {
            State.WAITING -> {
                if (timer.seconds() >= 10.0) {
                    state = State.LAUNCHING
                }
            }
            State.LAUNCHING -> {
                launch.shoot()
                if (timer.seconds() >= 20.0) {
                    launch.stop()
                    state = State.DRIVING
                    Scheduler.schedule(PedroCommands.follow(follower, park()))

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