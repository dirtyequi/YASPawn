package org.lichtspiele.yasp;

import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.lichtspiele.dbb.MessageBase;
import org.lichtspiele.dbb.exception.TranslationFileNotFoundException;
import org.lichtspiele.dbb.exception.TranslationNotFoundException;

public class Messages extends MessageBase {

	public Messages(JavaPlugin plugin) throws TranslationFileNotFoundException {
		super(plugin);
	}
	
	/*
	 * worldlist
	 */
	public void worldListTitle(CommandSender sender) throws TranslationNotFoundException {
		String message =				
			this.translation.getTranslation("world_list_header");
		this.send(sender, message);		
	}

	public void worldListEntry(CommandSender sender, String world, Boolean disabled) throws TranslationNotFoundException {
		if (disabled) {
			this.send(sender, this.translation.getTranslation("world_list_entry", 
				new String[] {"world", world, "state", this.translation.getTranslation("disabled") } ));
		} else {
			this.send(sender, this.translation.getTranslation("world_list_entry", 
				new String[] {"world", world, "state", this.translation.getTranslation("enabled") } ));			
		}
	}
	
	/*
	 * enable/disable world
	 */
	public void worldSpawnState(CommandSender sender, String world, boolean enabled) throws TranslationNotFoundException {
		if (enabled) {
			this.send(sender, this.translation.getTranslation("world_spawn_state", 
				new String[] { "world", world, "state", this.translation.getTranslation("enabled") } ));
		} else {
			this.send(sender, this.translation.getTranslation("world_spawn_state", 
				new String[] { "world", world, "state", this.translation.getTranslation("disabled") } ));			
		}
	}

	public void worldSpawnStateAlreadyApplied(CommandSender sender, String world, boolean enabled) 
			throws TranslationNotFoundException {
		if (enabled) {
			this.send(sender, this.translation.getTranslation("world_spawn_state_already_applied", 
				new String[] { "world", world, "state", this.translation.getTranslation("enabled") } ));
		} else {
			this.send(sender, this.translation.getTranslation("world_spawn_state", 
				new String[] { "world", world, "state", this.translation.getTranslation("disabled") } ));			
		}
	}	
	
	
	public void enableWorld(CommandSender sender, String world) throws TranslationNotFoundException {
		this.send(sender, this.translation.getTranslation("enable_world_success", new String[] {"world", world} ));
	}

	public void enableWorldAlreadyEnabled(CommandSender sender, String world) throws TranslationNotFoundException {
		this.send(sender, this.translation.getTranslation("enable_world_already_enabled", new String[] {"world", world} ));
	}	

	/*
	 * disable world
	 */
	public void disableWorld(CommandSender sender, String world) throws TranslationNotFoundException {
		this.send(sender, this.translation.getTranslation("disable_world_success", new String[] {"world", world} ));
	}

	public void disableWorldAlreadyDisabled(CommandSender sender, String world) throws TranslationNotFoundException {
		this.send(sender, this.translation.getTranslation("disable_world_already_disabled", new String[] {"world", world} ));
	}	
	
		
	/*
	 * spawn
	 */
	public void spawn(CommandSender sender, String world, boolean prefix) throws TranslationNotFoundException {
		this.send(sender, this.translation.getTranslation("spawn_message_world", new String[] {"world", world} ), prefix);		
	}

	public void spawn(CommandSender sender, boolean prefix) throws TranslationNotFoundException {
		this.send(sender, this.translation.getTranslation("spawn_message"), prefix);
	}
	


	/*
	 * default-world
	 */
	public void defaultWorld(CommandSender sender, String world) throws TranslationNotFoundException {
		this.send(sender, this.translation.getTranslation("default_world_worldname", new String[] {"world", world} ));
	}

	
	/*
	 * single-spawn
	 */
	public void singleServerSpawn(CommandSender sender, Boolean enabled) throws TranslationNotFoundException {
		if (enabled) {
			this.send(sender, this.translation.getTranslation("single_spawn_state",
				new String[] { "state", this.translation.getTranslation("enabled") }
			));
		} else {
			this.send(sender, this.translation.getTranslation("single_spawn_state",
				new String[] { "state", this.translation.getTranslation("disabled") }
			));
		}
	}
	

	/*
	 * say-world-name
	 */	
	
	public void sayWorldName(CommandSender sender, boolean enabled) throws TranslationNotFoundException {
		if (enabled) {
			this.send(sender, this.translation.getTranslation("say_world_name_state", 
				new String[] { "state", this.translation.getTranslation("enabled") }
			));
		} else {
			this.send(sender, this.translation.getTranslation("say_world_name_state", 
				new String[] { "state", this.translation.getTranslation("disabled") }
			));
		}
	}
	
	/*
	 * prefix-on-spawn 
	 */
	public void prefixOnSpawn(CommandSender sender, boolean enabled) throws TranslationNotFoundException {
		if (enabled) {
			this.send(sender, this.translation.getTranslation("prefix_on_spawn_state",
				new String[] { "state", this.translation.getTranslation("enabled") }
			));
		} else {
			this.send(sender, this.translation.getTranslation("prefix_on_spawn_state",
					new String[] { "state", this.translation.getTranslation("disabled") }
			));
		}		
	}

	
	/*
	 * config
	 */
	public void config(CommandSender sender) throws TranslationNotFoundException {
		this.send(sender, this.translation.getTranslation("config_header"));	
	}
	
	
	/*
	 * locale
	 */
	public void locale(CommandSender sender) throws TranslationNotFoundException {
		this.send(sender, this.translation.getTranslation("set_locale",
			new String[] { "locale", this.translation.getLocale() }
		));	
	}

	
	/*
	 * help
	 */
	public void helpTitle(CommandSender sender) throws TranslationNotFoundException {
		this.send(sender, this.translation.getTranslation("help_header"));
	}
	
	public void helpEntry(CommandSender sender, String command, String[] args, String desc_lang_key) throws TranslationNotFoundException {
		this.send(sender, 
			ChatColor.AQUA + " /" + command  + ChatColor.GREEN + " " + StringUtils.join(args, " ") +
		    ChatColor.WHITE + " " + this.translation.getTranslation(desc_lang_key),
		false);	
	}

}


