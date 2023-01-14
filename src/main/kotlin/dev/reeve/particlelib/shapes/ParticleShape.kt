package dev.reeve.particlelib.shapes

import com.okkero.skedule.CoroutineTask
import com.okkero.skedule.schedule
import dev.reeve.particlelib.BasicShapeData
import dev.reeve.particlelib.Particle
import dev.reeve.particlelib.Particles
import dev.reeve.particlelib.utility.Dirtyable
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import org.bukkit.util.Vector

abstract class ParticleShape(val basicShapeData: BasicShapeData) {
	private lateinit var particles: Particles
	var dirty = true
	
	open fun tick() {
		if (dirty) {
			println("Dirty, creating new particles")
			if (!::particles.isInitialized) {
				particles = Particles(createParticles())
			} else {
				particles.particles = createParticles()
			}
			dirty = false
		}
		particles.tick()
	}
	
	open fun tickFor(duration: Long, plugin: Plugin): CoroutineTask {
		return Bukkit.getScheduler().schedule(plugin) {
			repeating(1)
			for (i in 0 until duration) {
				tick()
				if (i % 40 == 0L) {
					println("Ticked $i times")
				}
				updateParticles(i, particles.particles)
				yield()
			}
		}
	}
	
	fun MutableList<Particle>.addParticle(vector: Vector) {
		with (basicShapeData) {
			rotate(vector)
			
			add(Particle(location.value.clone().add(vector), vector, particle.value, data.value))
		}
	}
	
	fun addCallbackForDirties(vararg dirtyables: Dirtyable<*>) {
		dirtyables.forEach { it.onDirty = { dirty = true } }
	}
	
	abstract fun createParticles(): MutableList<Particle>

	open fun updateParticles(time: Long, particles: List<Particle>) {
	
	}
}