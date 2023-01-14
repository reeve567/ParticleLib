package dev.reeve.particlelib

import dev.reeve.particlelib.shapes.ParticleCircle
import dev.reeve.particlelib.shapes.ParticleExplosion
import dev.reeve.particlelib.shapes.ParticleSphere
import org.bukkit.Color
import org.bukkit.Particle
import org.bukkit.Particle.DustOptions
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class ParticleLib: JavaPlugin() {
	override fun onEnable() {
	
	}
	
	override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
		if (sender !is Player) return false
		
		if (command.name == "particlecircle") {
			val location = sender.location
			val radius = args[0].toDouble()
			val amount = args[1].toInt()
			println("Creating circle, radius: $radius, amount: $amount")
			val particle = ParticleCircle(BasicShapeData(location, amount, Particle.REDSTONE, DustOptions(Color.AQUA, 1f)), radius)
			particle.tickFor(500, this)
			sender.sendMessage("Created circle")
			return true
		} else if (command.name == "particlesphere") {
			val location = sender.location
			val radius = args[0].toDouble()
			val amount = args[1].toInt()
			println("Creating sphere, radius: $radius, amount: $amount")
			val particle = ParticleSphere(BasicShapeData(location, amount, Particle.REDSTONE, DustOptions(Color.AQUA, 1f)), radius)
			particle.tickFor(500, this)
			sender.sendMessage("Created sphere")
			return true
		} else if (command.name == "particleexplosion") {
			val location = sender.location
			val radius = args[0].toDouble()
			val amount = args[1].toInt()
			val speed = args[2].toDouble()
			val randomStrength = args[3].toDouble()
			val offsetX = args[4].toDouble()
			val offsetY = args[5].toDouble()
			val offsetZ = args[6].toDouble()
			println("Creating explosion, radius: $radius, amount: $amount, speed: $speed, randomStrength: $randomStrength")
			val particle = ParticleExplosion(BasicShapeData(location.add(offsetX, offsetY, offsetZ), amount, Particle.REDSTONE, DustOptions(Color.FUCHSIA, 1f)), radius, speed, randomStrength)
			particle.tickFor(50, this)
			sender.sendMessage("Created explosion")
			return true
		}
		
		return super.onCommand(sender, command, label, args)
	}
}