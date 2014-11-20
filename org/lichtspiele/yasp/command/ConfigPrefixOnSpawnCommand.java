package org.lichtspiele.yasp.command;

import org.bukkit.command.CommandSender ;
import org.bukkit.plugin.java.JavaPlugin;
import org.lichtspiele.dbb.command.PluginCommandBase;
import org.lichtspiele.dbb.exception.CommandSenderIsNotPlayerException;
import org.lichtspiele.dbb.exception.InsufficientPermissionException;
import org.lichtspiele.dbb.exception.InvalidCommandException;
import org.lichtspiele.dbb.exception.TranslationNotFoundException;
import org.lichtspiele.yasp.Messages;

public class ConfigPrefixOnSpawnCommand extends PluginCommandBase {
	
	protected String permission		= "yasp.admin.prefixonspawn";
	
	public ConfigPrefixOnSpawnCommand(JavaPlugin plugin, CommandSender sender)
			throws InsufficientPermissionException, CommandSenderIsNotPlayerException {
		super(plugin, sender);
	}
	
	public void call(Messages messages, String args[]) throws InvalidCommandException, TranslationNotFoundException {
		if (args.length == 0) {
			messages.prefixOnSpawn(this.sender, this.config.getBoolean("prefix-on-spawn"));
		
		} else if (args.length == 1){ 
			if (args[0].equalsIgnoreCase("enable")) {
				this.config.set("prefix-on-spawn", true);
			} else if (args[0].equalsIgnoreCase("disable")) {
				this.config.set("prefix-on-spawn", false);			
			} else {
				throw new InvalidCommandException();				
			}
			
			this.plugin.saveConfig();
			this.call(messages, new String[0]);
		} else {
			throw new InvalidCommandException();
		}
	}

}
