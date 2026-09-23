package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.Gamepad
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.Telemetry



class ArcadeDrive  {
    private lateinit var left: DcMotor
    private lateinit var right: DcMotor

    fun init(hardwareMap: HardwareMap) {
        left = hardwareMap.get(DcMotor::class.java, "left")
        right = hardwareMap.get(DcMotor::class.java, "right")

        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE)
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE)

        left.direction = DcMotorSimple.Direction.REVERSE
        right.direction = DcMotorSimple.Direction.REVERSE
    }

    fun run(gamepad1: Gamepad) {

        val yAxis = -gamepad1.left_stick_y
        val xAxis = gamepad1.right_stick_x

        left.setPower((yAxis + xAxis).toDouble())
        right.setPower((yAxis - xAxis).toDouble())
    }

    fun telem(telemetry: Telemetry) {
        telemetry.addData("LeftDrivePower: ", left.getPower())
        telemetry.addData("RightDrivePower: ", right.getPower())
    }



}