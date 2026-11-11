package org.firstinspires.ftc.teamcode.ops

import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.Intake
import org.firstinspires.ftc.teamcode.Launcher
import org.firstinspires.ftc.teamcode.MecanumDrive
import com.pedropathing.api.Paths.line
import com.pedropathing.api.Paths.path
import com.pedropathing.api.PoseFactory
import com.pedropathing.follower.Follower
import com.pedropathing.ivy.Scheduler
import com.pedropathing.ivy.Scheduler.schedule
import com.pedropathing.ivy.pedro.PedroCommands.follow
import com.pedropathing.math.Pose
import com.pedropathing.paths.Path
import com.pedropathing.paths.curves.Curve
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.pedro.Constants
import com.pedropathing.ivy.Command
import com.pedropathing.ivy.Scheduler.schedule
import com.pedropathing.ivy.groups.Groups.sequential
import com.pedropathing.ivy.pedro.PedroCommands.follow
import com.pedropathing.paths.curves.bezier.BezierCurve
import com.qualcomm.robotcore.util.ElapsedTime

@TeleOp(name = "TeleOp", group = "OpMode")
class Tele : OpMode() {
    private lateinit var follower: Follower
    var drive = MecanumDrive()
    var launch = Launcher() //this includes the windmill
    var intake = Intake()

    override fun init() { /*
    code that loops after driver presses "init" on driver hub and before driver presses "start"
    */
        follower = Constants.create(hardwareMap)
        follower.setPose(Pose(0.0, 0.0, 0.0))
        drive.init(hardwareMap)
        launch.init(hardwareMap)
        intake.init(hardwareMap)

        drive.telem(telemetry)
        launch.telem(telemetry)
        intake.telem(telemetry)
        telemetry.update()
    }
    override fun start() { // code that executes ONCE when driver presses "play"

    }
    override fun loop() { // code that loops after driver presses "play" on the driver hub
        follower.update()
        if (!drive.getJoyStat(gamepad1) && (launch.rTrigStat(gamepad1)
                    || launch.lTrigStat(gamepad1))) {
            follower.hold(follower.pose())
        } else {
               drive.run(gamepad1)
        }
        /*
        Above this is the automatic holding function:
        Whenever the all joystick values are under the set dead zone AND
        the triggers (launcher) are over the set dead zone it sets the robot to hold position.
         */

        launch.control(gamepad1)
        drive.telem(telemetry)
        intake.telem(telemetry)
        launch.telem(telemetry)
        telemetry.update()

    }
}