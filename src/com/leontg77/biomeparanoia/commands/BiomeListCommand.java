package com.leontg77.biomeparanoia.commands;

import java.util.List;

import org.bukkit.block.Biome;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import com.leontg77.biomeparanoia.Utils;
import com.leontg77.biomeparanoia.Main;

/**
 * Biome list command.
 * <p> 
 * Command getting a list of all biome colors.
 * 
 * @author LeonTG77
 */
public class BiomeListCommand implements CommandExecutor, TabCompleter {
	private final Main plugin;

	public BiomeListCommand(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		if (!plugin.enabled) {
			sender.sendMessage(Main.PREFIX + "BiomeParanoia is not enabled.");
			return true;
		}
		
		final StringBuilder biomeList = new StringBuilder();
		int i = 1;
		
		for (Biome biome : Biome.values()) {
			// the biome isn't sendable (Like it has multiple with the same name, 
			// ex ExtremeHills and ExtremeHills+ that are the same color.
			if (!Utils.isSendable(biome)) {
				continue;
			}
			
			if (biomeList.length() > 0) {
				if (Biome.values().length == i) {
					biomeList.append("§f and ");
				} else {
					biomeList.append("§f, ");
				}
			}
			
			biomeList.append(Utils.getBiomeColor(biome) + Utils.capitalizeString(biome.name(), true));
			i++;
		}

		sender.sendMessage(Main.PREFIX + "All biome colors:");
		sender.sendMessage("§8» §f" + biomeList.toString().trim());
		return true;
	}
	
	@Override
	public List<String> onTabComplete(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		// no argurements.
		return null;
	}
}