package dev.reeve.particlelib

import com.okkero.skedule.schedule
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin

class Particles(var particles: List<Particle>) {
	fun tick() {
		particles.forEach { it.tick() }
	}
}