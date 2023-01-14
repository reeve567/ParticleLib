package dev.reeve.particlelib.shapes

import dev.reeve.particlelib.BasicShapeData
import dev.reeve.particlelib.Particle
import dev.reeve.particlelib.utility.Dirtyable
import org.bukkit.util.Vector
import kotlin.math.sqrt

/**
 * A cross shape (X) in a square, flat plane (XZ).
 * Possible improvements:
 * - Add a way to change the size to be a rectangle, size x versus size z
 */
class ParticleCross(basicShapeData: BasicShapeData, size: Double, amount: Int): ParticleShape(basicShapeData) {
	val size = Dirtyable(size)
	val amount = Dirtyable(amount)
	
	override fun createParticles(): MutableList<Particle> {
		val particles = mutableListOf<Particle>()
		val sqrtTwo = sqrt(2.0)
		
		val lineOneAmount = amount.value / 2
		val lineTwoAmount = amount.value - lineOneAmount
		
		val length = size.value * sqrtTwo
		
		for (i in 0 until lineOneAmount) {
			val increment = length / lineOneAmount
			
			val x = (-size.value / 2.0) + (increment * i)
			val z = (-size.value / 2.0) + (increment * i)
			
			val vector = Vector(x, 0.0, z)
			particles.addParticle(vector)
		}
		
		for (i in 0 until lineTwoAmount) {
			val increment = length / lineOneAmount
			
			val x = (-size.value / 2.0) + (increment * i)
			val z = (size.value / 2.0) + (increment * i)
			
			val vector = Vector(x, 0.0, z)
			particles.addParticle(vector)
		}
		
		return particles
	}
}