package org.lichtspiele.yasp.command;

import java.util.List; 

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.lichtspiele.dbb.command.PluginCommandBase;
import org.lichtspiele.dbb.exception.CommandSenderIsNotPlayerException;
import org.lichtspiele.dbb.exception.InsufficientPermissionException;
import org.lichtspiele.dbb.exception.InvalidCommandException;
import org.lichtspiele.dbb.exception.TranslationNotFoundException;
import org.lichtspiele.yasp.Messages;
import org.lichtspiele.yasp.Worlds;

public class SpawnWorldCommand extends PluginCommandBase {

	protected String permission			= "yasp.spawn.world";
	
	protected boolean mustBePlayer		= true;
	
	private Worlds worlds				= null;
	
	@SuppressWarnings("unchecked")
	public SpawnWorldCommand(JavaPlugin plugin, CommandSender sender)
			throws InsufficientPermissionException, CommandSenderIsNotPlayerException {
		
		super(plugin, sender);
		
		this.worlds = new Worlds((List<Object>) this.config.getList("disabled-worlds"));
	}
	
	public void call(Messages messages, String[] args) throws InvalidCommandException, TranslationNotFoundException {		
		if (args.length == 1) {
			
			if (!this.worlds.worldExists(args[0])) {
				messages.unknownWorld(sender, args[0]);
				return;
			}
			
			World world = Bukkit.getServer().getWorld(args[0]);
			
			Location wsl = world.getSpawnLocation();
			Location location = new Location(world, wsl.getX(), wsl.getY(), wsl.getZ(), wsl.getYaw(), wsl.getPitch());
			
			this.player.teleport(location);
			
			if (this.config.getBoolean("say-world-name") && !this.config.getBoolean("single-server-spawn")) {
				messages.spawn(this.player, world.getName(), this.config.getBoolean("prefix-on-spawn"));
			} else {
				messages.spawn(this.player, this.config.getBoolean("prefix-on-spawn"));
			}			
		} else {
			throw new InvalidCommandException();
		}
				

	}

}
