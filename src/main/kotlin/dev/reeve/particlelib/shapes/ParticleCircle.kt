package dev.reeve.particlelib.shapes

import dev.reeve.particlelib.BasicShapeData
import dev.reeve.particlelib.Particle
import dev.reeve.particlelib.utility.Dirtyable
import org.bukkit.util.Vector
import kotlin.math.cos
import kotlin.math.sin

/**
 * Class for creating a circle of particles
 * @param radius The radius of the circle
 */
class ParticleCircle(
	basicShapeData: BasicShapeData, radius: Double,  val bounds: Bounds = Bounds()
) : ParticleShape(basicShapeData) {
	val radius = Dirtyable(radius)
	
	init {
		addCallbackForDirties(this.radius)
		basicShapeData.addDirtyProperties(this)
	}
	
	override fun createParticles(): MutableList<Particle> {
		val particles = mutableListOf<Particle>()
		
		for (i in 0 until basicShapeData.amount.value) {
			val angle = (i / basicShapeData.amount.value.toDouble()) * 2 * Math.PI
			if (bounds.x.value == null || bounds.x.value!!.any { pair -> pair.first <= angle && pair.second >= angle  }) {
				val x = radius.value * cos(angle)
				val z = radius.value * sin(angle)
				
				val vector = Vector(x, 0.0, z)
				particles.addParticle(vector)
				println("Created particle $i at $x, $z")
			}
		}
		
		println("Center: ${basicShapeData.location.value}")
		
		return particles
	}
	
	class Bounds(
		x: List<Pair<Double, Double>>? = null
	) {
		val x = Dirtyable(x)
	}
}