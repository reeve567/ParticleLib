package dev.reeve.particlelib.shapes

import dev.reeve.particlelib.BasicShapeData
import dev.reeve.particlelib.Particle
import dev.reeve.particlelib.utility.Dirtyable
import org.bukkit.util.Vector
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

/**
 * A particle explosion, which is a particle shape that spawns particles in a sphere.
 * @param basicShapeData The data for the shape, such as the location, particle, and rotations.
 * @param radius The radius of the initial explosion.
 */
open class ParticleSphere(basicShapeData: BasicShapeData, radius: Double, val bounds: Bounds = Bounds()) : ParticleShape(basicShapeData) {
	val radius = Dirtyable(radius)
	
	init {
		addCallbackForDirties(this.radius)
		basicShapeData.addDirtyProperties(this)
	}
	
	override fun createParticles(): MutableList<Particle> {
		val particles = mutableListOf<Particle>()
		val phi = Math.PI * (3 - sqrt(5.0))

		for (i in 0 until basicShapeData.amount.value) {
			val y = 1 - (i.toDouble() / (basicShapeData.amount.value - 1)) * 2
			val radius = sqrt(1 - y * y)
			val theta = phi * i
			val x = cos(theta) * radius
			val z = sin(theta) * radius
			val vector = Vector(x, y, z).multiply(this.radius.value)
			
			particles.addParticle(vector)
		}
		
		return particles
	}
	
	class Bounds(
		x: List<Pair<Double, Double>>? = null, y: List<Pair<Double, Double>>? = null, z: List<Pair<Double, Double>>? = null
	) {
		val x = Dirtyable(x)
		val y = Dirtyable(y)
		val z = Dirtyable(z)
	}
}