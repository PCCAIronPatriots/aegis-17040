package org.firstinspires.ftc.teamcode.subsystems.limelight

data class GroupObservation(   // Average position of detected tags in robot space.
    val group: TargetGroup,
    val averageX: Double,
    val averageY: Double,
    val averageZ: Double,
    val visibleTagIds: List<Int>,
    val averageArea: Double,
    val averageTx: Double,
)