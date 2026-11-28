package org.firstinspires.ftc.teamcode.subsystems.limelight

data class VisionResult(
    val valid: Boolean,
    val tags: List<TagObservation>,
    val groups: List<GroupObservation>
)