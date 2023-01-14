package dev.reeve.particlelib

import dev.reeve.particlelib.shapes.ParticleShape
import dev.reeve.particlelib.utility.Dirtyable
import org.bukkit.Axis
import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.util.Vector

/**
 * Container for useful data for the creation of a particle shape, as well as some utilities around that data.
 * @param location The location of the center of the shape
 * @param particle The particle to spawn, look at the [org.bukkit.Particle] class for the list.
 * @param data The data of the particle, if it has any. The correct data to used can be found in the [org.bukkit.Particle] class.
 * @param rotations The rotations to apply to the circle, using radians (PI = 180 degrees | deg * PI / 180 = radians)
 */
class BasicShapeData(
	location: Location, amount: Int, particle: Particle, data: Any? = null, rotations: HashMap<Axis, Double> = HashMap()
) {
	val location = Dirtyable(location)
	val amount = Dirtyable(amount)
	val particle = Dirtyable(particle)
	val data = Dirtyable(data)
	val rotations = Dirtyable(rotations)
	
	fun addDirtyProperties(particleShape: ParticleShape) {
		particleShape.addCallbackForDirties(location, amount, particle, data, rotations)
	}
	
	fun rotate(vector: Vector) {
		rotations.value.forEach { (axis, angle) ->
			when (axis) {
				Axis.X -> vector.rotateAroundX(angle)
				Axis.Y -> vector.rotateAroundY(angle)
				Axis.Z -> vector.rotateAroundZ(angle)
			}
		}
	}
}