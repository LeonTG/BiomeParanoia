package com.leontg77.biomeparanoia.cmds;

import org.bukkit.block.Biome;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.leontg77.biomeparanoia.BiomeUtils;
import com.leontg77.biomeparanoia.Main;

/**
 * Bl command.
 * <p> 
 * Command getting a list of all biome colors.
 * 
 * @author LeonTG77
 */
public class BlCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// if the plugin is disabled, tell them so and return.
		if (!Main.enabled) {
			sender.sendMessage(Main.PREFIX + "BiomeParanoia is not enabled.");
			return true;
		}
		
		StringBuilder biomes = new StringBuilder();
		
		// loop thru all biomes.
		for (Biome b : Biome.values()) {
			// the biome isn't sendable (Like it has multiple with the same name, 
			// ex ExtremeHills and ExtremeHills+ that are the same color.
			if (!BiomeUtils.isSendable(b)) {
				continue;
			}
			
			// add ,'s between every biome.
			if (biomes.length() > 0) {
				biomes.append("§f, ");
			}
			
			// add the biome with its color to the strinbuilder.
			biomes.append(BiomeUtils.biomeColor(b) + b.name().substring(0, 1).toUpperCase() + b.name().substring(1).toLowerCase().replaceAll("_", " "));
		}

		// send them the biomes.
		sender.sendMessage(Main.PREFIX + "List of all biome colors:");
		sender.sendMessage(biomes.toString().trim());
		return true;
	}
}