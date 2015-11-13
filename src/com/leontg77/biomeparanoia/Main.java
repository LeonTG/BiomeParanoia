package com.leontg77.biomeparanoia;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.leontg77.biomeparanoia.cmds.BParanoiaCommand;
import com.leontg77.biomeparanoia.cmds.BlCommand;

/**
 * Main class of the plugin.
 * 
 * @author LeonTG77
 */
public class Main extends JavaPlugin {
	public static Main plugin;
	
	public static final String PREFIX = "§7[§6BP§7] §f";
	public static boolean enabled = false;

	@Override
	public void onDisable() {
		// print a message to the console saying it has been disabled.
		PluginDescriptionFile file = getDescription();
		getLogger().info(file.getName() + " has been disabled.");
		
		// set the plugin field to null.
		plugin = null;
	}
	
	@Override
	public void onEnable() {
		// print a message to the console saying it has been enabled.
		PluginDescriptionFile file = getDescription();
		getLogger().info(file.getName() + " v" + file.getVersion() + " has been enabled.");
		getLogger().info("Plugin is made by LeonTG77.");
		
		// register the /bl and /bparanoia commands.
		getCommand("bl").setExecutor(new BlCommand());
		getCommand("bparanoia").setExecutor(new BParanoiaCommand());
		
		// set the plugin field to this class.
		plugin = this;
	}
}