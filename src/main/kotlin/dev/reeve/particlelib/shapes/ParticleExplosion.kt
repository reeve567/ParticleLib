package dev.reeve.particlelib.shapes

import dev.reeve.particlelib.BasicShapeData
import dev.reeve.particlelib.Particle
import dev.reeve.particlelib.utility.Dirtyable
import kotlin.random.Random

/**
 * A particle explosion, which is a particle shape that spawns particles in a sphere.
 * @param basicShapeData The data for the shape, such as the location, particle, and rotations.
 * @param radius The radius of the initial explosion.
 * @param speed The speed of the particles. This is used with radius to add to the location of the particle, so imagine using a speed of 1, it will double in distance every tick
 * @param randomStrength Determines how much random will be added to the particles' location.
 * @param bounds The bounds of the explosion, which will determine where the particles can spawn, using radians.
 */
class ParticleExplosion(basicShapeData: BasicShapeData, radius: Double, speed: Double, randomStrength: Double, bounds: Bounds = Bounds()) : ParticleSphere(basicShapeData, radius, bounds) {
	val speed = Dirtyable(speed)
	val randomStrength = Dirtyable(randomStrength)
	
	init {
		addCallbackForDirties(this.radius)
		basicShapeData.addDirtyProperties(this)
	}
	
	override fun createParticles(): MutableList<Particle> {
		val particles = super.createParticles()
		
		if (randomStrength.value != 0.0) {
			particles.forEach {
				it.location.add(it.vector.clone().multiply(randomStrength.value * (Random.nextDouble() - 0.5)))
			}
		}
		
		return particles
	}
	
	override fun updateParticles(time: Long, particles: List<Particle>) {
		particles.forEach {
			it.location.add(it.vector.clone().multiply(speed.value))
		}
	}
}