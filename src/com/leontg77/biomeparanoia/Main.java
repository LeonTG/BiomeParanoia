package com.leontg77.biomeparanoia;

import org.bukkit.block.Biome;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.leontg77.biomeparanoia.commands.BParanoiaCommand;
import com.leontg77.biomeparanoia.commands.BiomeListCommand;
import com.leontg77.biomeparanoia.listeners.GamemodeListener;
import com.leontg77.biomeparanoia.listeners.MoveListener;

/**
 * Main class of the plugin.
 * 
 * @author LeonTG77
 */
public class Main extends JavaPlugin {
	public static final String PREFIX = "§5Biome Paranoia §8» §7";
	
	public boolean enabled = false;

	@Override
	public void onDisable() {
		final PluginDescriptionFile file = getDescription();
		getLogger().info(file.getName() + " has been disabled.");
	}
	
	@Override
	public void onEnable() {
		final PluginDescriptionFile file = getDescription();
		getLogger().info(file.getName() + " v" + file.getVersion() + " has been enabled.");
		getLogger().info("The plugin is made by LeonTG77.");

		final GamemodeListener gamemode = new GamemodeListener();
		final MoveListener move = new MoveListener(this);
		
		final BParanoiaCommand mainCommand = new BParanoiaCommand(this, gamemode, move);
		final BiomeListCommand listCommand = new BiomeListCommand(this);
		
		// register command.
		getCommand("bparanoia").setExecutor(mainCommand);
		getCommand("bparanoia").setTabCompleter(mainCommand);
		
		getCommand("biomelist").setExecutor(listCommand);
		getCommand("biomelist").setTabCompleter(listCommand);
		
		for (Biome biome : Utils.SENDABLE_BIOMES) {
			getConfig().addDefault(biome.name().toLowerCase(), Utils.getBiomeColor(biome, this));
		}
		
		getConfig().options().copyDefaults(true);
		
		saveConfig();
		reloadConfig();
	}
}