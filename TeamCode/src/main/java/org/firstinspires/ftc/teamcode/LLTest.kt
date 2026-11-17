package org.firstinspires.ftc.teamcode
import com.qualcomm.hardware.limelightvision.Limelight3A
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.eventloop.opmode.OpMode


@TeleOp(name = "LLTest", group = "OpMode")
class LLTest : OpMode() {
    private lateinit var limelight: Limelight3A


    override fun init() {
            limelight = hardwareMap.get(Limelight3A::class.java, "limelight")
        telemetry.msTransmissionInterval = 11

            limelight.pipelineSwitch(0)


            limelight.start()
        }
    override fun loop() {
            val result = limelight.getLatestResult()
            if (result != null) {
                if (result.isValid()) {
                    val botPose = result.botpose
                    telemetry.addData("tx", result.tx)
                    telemetry.addData("ty", result.ty)
                    telemetry.addData("BotPose", botPose.toString())
                }
            }
    }
}


