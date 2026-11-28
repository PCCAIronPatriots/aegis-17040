package org.firstinspires.ftc.teamcode.subsystems.limelight

data class TagObservation(
    val id: Int,
    val group: TargetGroup,
    val x: Double,
    val y: Double,
    val z: Double,
    val area: Double,
    val tx: Double
)