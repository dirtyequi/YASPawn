package org.lichtspiele.yaspawn.command;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.lichtspiele.dbb.command.PluginCommandBase;
import org.lichtspiele.dbb.exception.CommandSenderIsNotPlayerException;
import org.lichtspiele.dbb.exception.InsufficientPermissionException;
import org.lichtspiele.dbb.exception.TranslationNotFoundException;
import org.lichtspiele.yaspawn.Messages;

public class SpawnCommand extends PluginCommandBase {
	
	public SpawnCommand(JavaPlugin plugin, CommandSender sender)
			throws InsufficientPermissionException, CommandSenderIsNotPlayerException {
		
		super(plugin, sender, "yaspawn.spawn", true);
	}
	
	public boolean call(Messages messages, String[] args) throws TranslationNotFoundException {
		World world = null;
			
		if (
				(this.config.getBoolean("single-server-spawn")) || 					
				(this.config.getList("disabled-worlds")).contains(this.player.getWorld().getName().toString())
			) {

				String default_world = this.config.getString("default-world");
				world = Bukkit.getServer().getWorld(default_world);
			} else {
				world = this.player.getWorld();
			}		
		
		Location wsl = world.getSpawnLocation();
		Location location = new Location(world, wsl.getX(), wsl.getY(), wsl.getZ(), wsl.getYaw(), wsl.getPitch());
		
		this.player.teleport(location);
		
		if (this.config.getBoolean("say-world-name") && !this.config.getBoolean("single-server-spawn")) {
			messages.spawn(this.player, world.getName(), this.config.getBoolean("prefix-on-spawn"));
		} else {
			messages.spawn(this.player, this.config.getBoolean("prefix-on-spawn"));
		}
		
		return true;
	}

}
