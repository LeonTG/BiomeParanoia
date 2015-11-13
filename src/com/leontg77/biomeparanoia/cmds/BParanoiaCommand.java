package com.leontg77.biomeparanoia.cmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;

import com.leontg77.biomeparanoia.Main;
import com.leontg77.biomeparanoia.listeners.MoveListener;

/**
 * BParanoia command.
 * <p> 
 * Command used to enable or disable the scenario.
 * 
 * @author LeonTG77
 */
public class BParanoiaCommand implements CommandExecutor {
	private static final String PERMISSION = "bparanoia.manage";
	private MoveListener move = new MoveListener();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// check if the have permission, if not send them a message and return.
		if (!sender.hasPermission(PERMISSION)) {
			sender.sendMessage(Main.PREFIX + ChatColor.RED + "You don't have access to this.");
			return true;
		}
		
		// check if they typed anything else than the command itself, if not send usage and return.
		if (args.length == 0) {
			sender.sendMessage(Main.PREFIX + "Usage: /bparanoia <enable|disable>");
			return true;
		}
		
		// check if they typed /egg enable, if so do the command.
		if (args[0].equalsIgnoreCase("enable")) {
			// check if the scenario is enabled, if not tell them so and return.
			if (Main.enabled) {
				sender.sendMessage(Main.PREFIX + "BiomeParanoia is already enabled.");
				return true;
			}
			
			// send them a message and set enabled to be true
			sender.sendMessage(Main.PREFIX + "BiomeParanoia has been enabled.");
			Main.enabled = true;
			
			// register the eventhandles for the scenario.
			PluginManager manager = Bukkit.getPluginManager();
			manager.registerEvents(move, Main.plugin);
			return true;
		}

		// check if they typed /egg enable, if so do the command.
		if (args[0].equalsIgnoreCase("disable")) {
			// check if the scenario wasn't enabled, if not tell them so and return.
			if (!Main.enabled) {
				sender.sendMessage(Main.PREFIX + "BiomeParanoia is not enabled.");
				return true;
			}

			// send them a message and set enabled to be false
			sender.sendMessage(Main.PREFIX + "BiomeParanoia has been disabled.");
			Main.enabled = false;
			
			// unregister the eventhandles for the scenario.
			HandlerList.unregisterAll(move);
			return true;
		}
		
		// they didn't type enable or disable, send usage.
		sender.sendMessage(Main.PREFIX + "Usage: /bparanoia <enable|disable>");
		return true;
	}
}