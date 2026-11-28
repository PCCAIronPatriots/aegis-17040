package org.firstinspires.ftc.teamcode.subsystems.limelight

import org.firstinspires.ftc.teamcode.Team

enum class TargetGroup(
    val alliance: Team,
    val side: FieldSide
) {
    RED_SCORING(Team.RED, FieldSide.SCORING),
    BLUE_SCORING(Team.BLUE, FieldSide.SCORING),
    RED_AUDIENCE(Team.RED, FieldSide.AUDIENCE),
    BLUE_AUDIENCE(Team.BLUE, FieldSide.AUDIENCE)
}