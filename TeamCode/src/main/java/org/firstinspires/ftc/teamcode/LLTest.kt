package org.firstinspires.ftc.teamcode
import com.qualcomm.hardware.limelightvision.Limelight3A
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.eventloop.opmode.OpMode


@TeleOp(name = "LLTest", group = "OpMode")
class LLTest : OpMode() {
    private lateinit var limelight: Limelight3A


    override fun init() {
            limelight = hardwareMap.get(Limelight3A::class.java, "limelight")
            telemetry.setMsTransmissionInterval(11)

            limelight.pipelineSwitch(0)


            limelight.start()
        }
    override fun loop() {
            val result = limelight.getLatestResult()
            if (result != null) {
                if (result.isValid()) {
                    val botpose = result.getBotpose()
                    telemetry.addData("tx", result.getTx())
                    telemetry.addData("ty", result.getTy())
                    telemetry.addData("Botpose", botpose.toString())
                }
            }
    }
}


