package org.firstinspires.ftc.teamcode.ops.teleop

import com.qualcomm.hardware.limelightvision.Limelight3A
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp(name = "LLTest", group = "OpMode")
class LLTest : OpMode() {
    private lateinit var limelight: Limelight3A


    /**
     * Initializes the Limelight camera and sets up transmission interval and pipelines.
     */
    override fun init() {
            limelight = hardwareMap.get(Limelight3A::class.java, "limelight")
             telemetry.msTransmissionInterval = 11
            limelight.pipelineSwitch(0)
            limelight.start()
        }

    /**
     * Periodically queries the Limelight results and logs targeting data to telemetry.
     */
    override fun loop() {
            val result = limelight.getLatestResult()
            if (result != null && result.isValid()) {
                    val botPose = result.botpose
                    telemetry.addData("tx", result.tx)
                    telemetry.addData("ty", result.ty)
                    telemetry.addData("BotPose", botPose.toString())
            }
    }
}