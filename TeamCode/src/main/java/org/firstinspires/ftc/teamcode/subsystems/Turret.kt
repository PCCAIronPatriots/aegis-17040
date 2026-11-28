package org.firstinspires.ftc.teamcode.subsystems

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.util.ElapsedTime
import com.qualcomm.robotcore.util.Range
import org.firstinspires.ftc.robotcore.external.Telemetry
import kotlin.math.abs
import org.firstinspires.ftc.teamcode.Constants



class Turret {
    private lateinit var turret: DcMotorEx
    private var kP = 0.0001
    private var kD = 0.0000
    private var goalX = 0
    private var lastError = 0.0
    private val angleTolerance = 0.2
    private var power = 0.0
    private val timer = ElapsedTime()
    val maxPower = Constants.MAX_TURRET_ROTATION_SPEED

    fun start(hardwareMap: HardwareMap) {
        turret = hardwareMap.get(DcMotorEx::class.java, "turret")
        turret.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        turret.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        turret.direction = DcMotorSimple.Direction.FORWARD
    }

    fun setkP(newKP : Double) {
        kP = newKP
    }

    fun getkP() : Double{
        return kP
    }

    fun setkD(newKD : Double) {
        kD = newKD
    }

    fun getkD() : Double {
        return kD
    }

    fun resetTime() {
        timer.reset()
    }

    fun loop(tx : Double, isValid : Boolean) {
        val deltaTime = timer.seconds()
        timer.reset()

        if (!isValid) {
            turret.power = 0.0
            lastError = 0.0
            return
        }

        val error = goalX - tx
        val pTerm = error * kP
        var dTerm = 0.0

        if (deltaTime > 0) {
             dTerm = ((error - lastError) / deltaTime) * kD
        }

        power = if (abs(error) < angleTolerance) {
            0.0
        } else {
            Range.clip(pTerm + dTerm, -maxPower, maxPower)
        }

        // max rotation check here (FIX BEFORE TESTING YOU COULD BREAK SOMETHING)
        turret.power = power
        lastError = error
    }

    fun telemetry(telemetry: Telemetry) {
        telemetry.addLine("TurretSetRotationSpeed: ${turret.power * 100}%")
        telemetry.addLine("CurTurretPower: ${turret.power * 100}% ")
    }
}