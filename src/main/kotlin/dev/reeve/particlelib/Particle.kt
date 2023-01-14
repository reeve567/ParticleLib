package dev.reeve.particlelib

import com.okkero.skedule.schedule
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.plugin.Plugin
import org.bukkit.util.Vector

data class Particle(var location: Location, var vector: Vector, var type: Particle, var data: Any? = null, var count: Int = 1) {
	fun tick() {
		location.world!!.spawnParticle(type, location, count, data)
	}
	
	fun tickFor(duration: Long, plugin: Plugin) {
		Bukkit.getScheduler().schedule(plugin) {
			repeating(1)
			for (i in 0..duration) {
				tick()
				yield()
			}
		}
	}
}