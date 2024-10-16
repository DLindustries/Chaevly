package net.ccbluex.liquidbounce.utils.aiming.anglesmooth

import net.ccbluex.liquidbounce.config.ChoiceConfigurable
import net.ccbluex.liquidbounce.utils.aiming.Rotation
import net.ccbluex.liquidbounce.utils.aiming.RotationManager
import net.ccbluex.liquidbounce.utils.entity.lastRotation
import net.ccbluex.liquidbounce.utils.kotlin.random
import net.minecraft.entity.Entity
import net.minecraft.util.math.Vec3d
import kotlin.math.*

class AccelerationSmoothMode(override val parent: ChoiceConfigurable<*>)
: AngleSmoothMode("Acceleration") {

    private val maxAcceleration by float("MaxAcceleration", 25f, 0f..100f)
    // TODO: figure out how to implement lower accel bound
    //private val minAcceleration by float("MinAcceleration", -25f, -100f..0f)
    private val accelerationError by float("AccelerationError", 0.1f, 0f..1f)
    private val constantError by float("ConstantError", 0.1f, 0f..10f)

    override fun limitAngleChange(
        factorModifier: Float,
        currentRotation: Rotation,
        targetRotation: Rotation,
        vec3d: Vec3d?,
        entity: Entity?
    ): Rotation {
        val prevRotation = RotationManager.previousRotation ?: player.lastRotation
        val prevYawDiff = RotationManager.angleDifference(currentRotation.yaw, prevRotation.yaw)
        val prevPitchDiff = RotationManager.angleDifference(currentRotation.pitch, prevRotation.pitch)
        val yawDiff = RotationManager.angleDifference(targetRotation.yaw, currentRotation.yaw)
        val pitchDiff = RotationManager.angleDifference(targetRotation.pitch, currentRotation.pitch)

        val (newYawDiff, newPitchDiff) = computeTurnSpeed(
            prevYawDiff,
            prevPitchDiff,
            yawDiff,
            pitchDiff,
        )

        return Rotation(
            currentRotation.yaw + newYawDiff,
            currentRotation.pitch + newPitchDiff
        )
    }

    override fun howLongToReach(currentRotation: Rotation, targetRotation: Rotation): Int {
        val prevRotation = RotationManager.previousRotation ?: player.lastRotation
        val prevYawDiff = RotationManager.angleDifference(currentRotation.yaw, prevRotation.yaw)
        val prevPitchDiff = RotationManager.angleDifference(currentRotation.pitch, prevRotation.pitch)
        val yawDiff = RotationManager.angleDifference(targetRotation.yaw, currentRotation.yaw)
        val pitchDiff = RotationManager.angleDifference(targetRotation.pitch, currentRotation.pitch)

        val (computedH, computedV) = computeTurnSpeed(
            prevYawDiff,
            prevPitchDiff,
            yawDiff,
            pitchDiff,
        )
        val lowest = min(computedH, computedV)

        if (lowest <= 0.0) {
            return 0
        }

        if (yawDiff == 0f && pitchDiff == 0f) {
            return 0
        }

        return (hypot(abs(yawDiff), abs(pitchDiff)) / lowest).roundToInt()
    }

    private fun computeTurnSpeed(prevYawDiff: Float,
                                 prevPitchDiff: Float,
                                 yawDiff: Float,
                                 pitchDiff: Float,
                                 ): Pair<Float, Float> {
        val yawAccel = RotationManager.angleDifference(yawDiff, prevYawDiff)
            .coerceIn(-maxAcceleration, maxAcceleration)
        val pitchAccel = RotationManager.angleDifference(pitchDiff, prevPitchDiff)
            .coerceIn(-maxAcceleration, maxAcceleration)

        val yawError = yawAccel * errorMult() + constantError()
        val pitchError = pitchAccel * errorMult() + constantError()

        return (prevYawDiff + yawAccel + yawError) to (prevPitchDiff + pitchAccel + pitchError)
    }

    fun errorMult() = (-accelerationError..accelerationError).random().toFloat()
    fun constantError() = (-constantError..constantError).random().toFloat()
}
