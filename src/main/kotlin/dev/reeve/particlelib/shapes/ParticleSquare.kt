package dev.reeve.particlelib.shapes

import dev.reeve.particlelib.BasicShapeData
import dev.reeve.particlelib.Particle
import dev.reeve.particlelib.utility.Dirtyable

class ParticleSquare(basicShapeData: BasicShapeData, size: Double) : ParticleShape(basicShapeData) {
	val size = Dirtyable(size)
	
	init {
		addCallbackForDirties(this.size)
		basicShapeData.addDirtyProperties(this)
	}
	
	override fun createParticles(): MutableList<Particle> {
		val particles = mutableListOf<Particle>()
		
		
		
		return particles
	}
}