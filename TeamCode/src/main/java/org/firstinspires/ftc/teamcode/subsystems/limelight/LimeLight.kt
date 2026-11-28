package org.firstinspires.ftc.teamcode.subsystems.limelight

import com.qualcomm.hardware.limelightvision.LLResult
import com.qualcomm.hardware.limelightvision.LLResultTypes
import com.qualcomm.hardware.limelightvision.Limelight3A
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit
import org.firstinspires.ftc.teamcode.Team

class LimeLight {
    private lateinit var limelight: Limelight3A
    private val pipelineIndex: Int = 0
    private val tagAssignments =
        mutableMapOf<Int, TargetGroup>()

    fun start(hardwareMap: HardwareMap, telemetry: Telemetry) {
        limelight = hardwareMap.get(
            Limelight3A::class.java,
            "limelight"
        )

        configureDefaultTags()
        limelight.pipelineSwitch(pipelineIndex)
        limelight.start()

    }

    fun loop(team: Team?, telemetry: Telemetry) {
        val result = getResult()
        telemetry.addData("Valid", result.valid)
        for (tag in result.tags) {
            telemetry.addData(
                "Tag ${tag.id}",
                "${tag.group} | " +
                        "X: %.2f Y: %.2f Z: %.2f".format(
                            tag.x, tag.y, tag.z
                        )
            )
        }
        val best = getBestTarget(alliance = team)
        telemetry.addData("Best Group", best?.group)
        telemetry.update()
    }

    fun stop() {
        limelight.stop()
    }

    private fun configureDefaultTags() {
        tagAssignments.clear()
        for (id in 30..33) {        // Red Scoring Tags: 30-33
            assignTag(id, TargetGroup.RED_SCORING)
        }
        for (id in 42..45) {        // Blue Scoring Tags: 42-45
            assignTag(id, TargetGroup.BLUE_SCORING)
        }
        for (id in 34..37) {        // Red Audience Tags: 34-37
            assignTag(id, TargetGroup.RED_AUDIENCE)
        }
        for (id in 38..41) {        // Blue Audience Tags: 38-41
            assignTag(id, TargetGroup.BLUE_AUDIENCE)
        }
    }

    fun assignTag(
        id: Int,
        group: TargetGroup
    ) {
        tagAssignments[id] = group
    }

    fun removeTag(id: Int) {
        tagAssignments.remove(id)
    }

    fun clearAssignments() {
        tagAssignments.clear()
    }

    fun getResult(): VisionResult { // read limelight results
        val result: LLResult =
            limelight.getLatestResult()
                ?: return emptyResult()
        if (!result.isValid()) {
            return emptyResult()
        }

        val observations = mutableListOf<TagObservation>()
        for (fiducial: LLResultTypes.FiducialResult    // Get EVERY visible AprilTag.
        in result.fiducialResults) {
            val tx = fiducial.targetXDegrees
            val id = fiducial.fiducialId
            val group = tagAssignments[id] ?: continue // Ignore tags that aren't assigned.
            val pose = fiducial.targetPoseRobotSpace // Get the tag's pose relative to the robot.
                ?: continue
            val position = pose.getPosition() // Convert position to inches.
                .toUnit(DistanceUnit.INCH)
            observations.add(
                TagObservation(
                    id = id,
                    group = group,
                    x = position.x,
                    y = position.y,
                    z = position.z,
                    area = fiducial.targetArea,
                    tx = tx,
                )
            )
        }

        val groupedTags = observations.groupBy {        // Group detections by their field location.
            it.group
        }

        val groupObservations = groupedTags.map { (group, tags) ->
            GroupObservation(
                group = group,
                averageX = tags.map { it.x }.average(),
                averageY = tags.map { it.y }.average(),
                averageZ = tags.map { it.z }.average(),
                visibleTagIds = tags.map { it.id },
                averageArea = tags.map { it.area }.average(),
                averageTx = tags.map { it.tx }.average()
            )
        }

        return VisionResult(
            valid = observations.isNotEmpty(),
            tags = observations,
            groups = groupObservations
        )
    }

    fun getBestTarget( // Returns the group with the most visible tags. (can be filtered by alliance)
        alliance: Team? = null
    ): GroupObservation? {
        return getResult().groups
            .filter {
                alliance == null ||
                        it.group.alliance == alliance
            }
            .maxWithOrNull(
                compareBy<GroupObservation> {
                    it.visibleTagIds.size
                }.thenBy {
                    it.averageArea
                }
            )
    }

    fun getTags( // returns tags pertaining to an alliance
        alliance: Team
    ): List<TagObservation> {
        return getResult().tags.filter {
            it.group.alliance == alliance
        }
    }

    fun getTags( // returns tags pertaining to a group
        group: TargetGroup
    ): List<TagObservation> {
        return getResult().tags.filter {
            it.group == group
        }
    }

    private fun emptyResult(): VisionResult {
        return VisionResult(
            valid = false,
            tags = emptyList(),
            groups = emptyList()
        )
    }
}