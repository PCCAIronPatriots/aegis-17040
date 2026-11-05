package org.firstinspires.ftc.teamcode

import com.pedropathing.api.Paths.*
import com.pedropathing.api.Paths
import com.pedropathing.api.PoseFactory
import com.pedropathing.follower.Follower
import com.pedropathing.math.Pose
import com.pedropathing.paths.Path
import com.pedropathing.ivy.Command
import com.pedropathing.ivy.Scheduler
import com.pedropathing.ivy.Scheduler.schedule
import com.pedropathing.ivy.commands.Commands.*
import com.pedropathing.ivy.groups.Groups.sequential
import com.pedropathing.ivy.pedro.PedroCommands.follow
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.pedro.Constants


@Autonomous(name = "Circle", group = "Autonomous")
class AutoPath : LinearOpMode() {

    private lateinit var follower: Follower

    private val poseFactory = PoseFactory.degrees()

    private val start = poseFactory.of(67.9073, 7.7688, 90.0)
    private val path1 = poseFactory.of(111.7214, 34.6127, 180.0)
    private val point2 = poseFactory.of(113.5676, 116.7023, -91.2883)
    private val point3 = poseFactory.of(26.9718, 113.2109, 2.3088)
    private val point4 = poseFactory.of(23.7204, 21.9973, 87.9585)
    private val point5 = poseFactory.of(131.6418, 34.4177, -173.4349)

    // Autonomous routine
    fun autoRoutine(): Command = sequential(
        follow(follower, path1()),
        follow(follower, path2()),
        follow(follower, path3()),
        follow(follower, path4()),
        follow(follower, path5())
    )

    override fun runOpMode() {
        Scheduler.reset()
        follower = Constants.create(hardwareMap)
        follower.setPose(start)
        follower.update()

        waitForStart()
        schedule(autoRoutine())

        while (opModeIsActive()) {
            follower.update()
            Scheduler.execute()

            telemetry.addData("x", follower.pose().x())
            telemetry.addData("y", follower.pose().y())
            telemetry.addData("heading", follower.pose().heading())

            if (follower.currentPath() != null) {
                telemetry.addData("Current path distance remaining", follower.distanceToEndpoint())
                telemetry.addData("Path number", follower.pathIndex())
            }

            telemetry.update()
        }
    }

    fun path1(): Path = Paths.line(start, path1).linear(start, path1)

    fun path2(): Path = Paths.line(path1, point2).reverseTangent()

    fun path3(): Path = Paths.line(point2, point3).reverseTangent()

    fun path4(): Path = Paths.line(point3, point4).reverseTangent()

    fun path5(): Path = Paths.line(point4, point5).reverseTangent()
}
