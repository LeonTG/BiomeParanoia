package com.leontg77.biomeparanoia.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

import com.google.common.collect.ImmutableList;
import com.leontg77.biomeparanoia.Main;
import com.leontg77.biomeparanoia.Utils;
import com.leontg77.biomeparanoia.listeners.GamemodeListener;
import com.leontg77.biomeparanoia.listeners.MoveListener;

/**
 * BParanoia command.
 * <p> 
 * Command used to enable or disable the scenario.
 * 
 * @author LeonTG77
 */
public class BParanoiaCommand implements CommandExecutor, TabCompleter {
	private static final String PERMISSION = "bparanoia.manage";

	private final GamemodeListener gamemode;
	private final MoveListener move;
	
	private final Main plugin;
	
	public BParanoiaCommand(Main plugin, GamemodeListener gamemode, MoveListener move) {
		this.plugin = plugin;
		
		this.gamemode = gamemode;
		this.move = move;
	}
	
	@Override
	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		if (args.length == 0) {
			sender.sendMessage(Main.PREFIX + "Usage: /bparanoia <info|enable|disable|setcolor>");
			return true;
		}
		
		if (args[0].equalsIgnoreCase("info")) {
			sender.sendMessage(Main.PREFIX + "Plugin creator: §aLeonTG77");
			sender.sendMessage(Main.PREFIX + "Description:");
			sender.sendMessage("§8» §f" + plugin.getDescription().getDescription());
			return true;
		}
		
		if (args[0].equalsIgnoreCase("enable")) {
			if (!sender.hasPermission(PERMISSION)) {
				sender.sendMessage(ChatColor.RED + "You don't have permission.");
				return true;
			}
			
			if (plugin.enabled) {
				sender.sendMessage(ChatColor.RED + "Biome Paranoia is already disabled.");
				return true;
			}
			
			Utils.broadcast(Main.PREFIX + "Biome Paranoia has been enabled.");
			plugin.enabled = true;

			Bukkit.getPluginManager().registerEvents(gamemode, plugin);
			Bukkit.getPluginManager().registerEvents(move, plugin);
			
			Utils.updatePlayers(plugin, ImmutableList.copyOf(Bukkit.getOnlinePlayers()));
			return true;
		}

		if (args[0].equalsIgnoreCase("disable")) {
			if (!sender.hasPermission(PERMISSION)) {
				sender.sendMessage(ChatColor.RED + "You don't have permission.");
				return true;
			}
			
			if (!plugin.enabled) {
				sender.sendMessage(ChatColor.RED + "Biome Paranoia is not enabled.");
				return true;
			}

			Utils.broadcast(Main.PREFIX + "Biome Paranoia has been disabled.");
			plugin.enabled = false;
			
			HandlerList.unregisterAll(gamemode);
			HandlerList.unregisterAll(move);
			
			for (Player online : Bukkit.getOnlinePlayers()) {
				online.setPlayerListName(null);
			}
			return true;
		}
		
		if (args[0].equalsIgnoreCase("setcolor")) {
			if (!sender.hasPermission(PERMISSION)) {
				sender.sendMessage(ChatColor.RED + "You don't have permission.");
				return true;
			}
			
			if (!plugin.enabled) {
				sender.sendMessage(ChatColor.RED + "Biome Paranoia is not enabled.");
				return true;
			}
			
			if (args.length < 3) {
				sender.sendMessage(Main.PREFIX + "Usage: /bparanoia setcolor <biome> <color>");
				return true;
			}
			
			Biome biome;
			
			try {
				biome = Biome.valueOf(args[1].toUpperCase());
				
				if (!Utils.isSendable(biome)) {
					throw new Exception();
				}
			} catch (Exception e) {
				sender.sendMessage(ChatColor.RED + "'" + args[1] + "' is not a vaild biome.");
				return true;
			}
			
			plugin.getConfig().set(biome.name().toLowerCase(), args[2]);
			plugin.saveConfig();

			Utils.updatePlayers(plugin, ImmutableList.copyOf(Bukkit.getOnlinePlayers()));
			
			String biomeColor = ChatColor.translateAlternateColorCodes('&', Utils.getBiomeColor(biome, plugin));
			sender.sendMessage(Main.PREFIX + "The biome color was changed to " + biomeColor + Utils.capitalizeString(biome.name(), true) + "§7.");
			return true;
		}
		
		sender.sendMessage(Main.PREFIX + "Usage: /bparanoia <info|enable|disable|setcolor>");
		return true;
	}

	@Override
	public List<String> onTabComplete(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final List<String> toReturn = new ArrayList<String>();
		final List<String> list = new ArrayList<String>();
		
		if (args.length == 1) {
			list.add("info");
			
			if (sender.hasPermission(PERMISSION)) {
				list.add("enable");
				list.add("disable");
				list.add("setcolor");
			}
		}
		
		if (args.length == 2) {
			if (sender.hasPermission(PERMISSION)) {
				for (Biome biome : Utils.SENDABLE_BIOMES) {
					list.add(biome.name().toLowerCase());
				}
			}
		}
		
		if (args.length == 3) {
			if (sender.hasPermission(PERMISSION)) {
				for (ChatColor color : ChatColor.values()) {
					list.add("&" + color.getChar());
				}
			}
		}

		// make sure to only tab complete what starts with what they 
		// typed or everything if they didn't type anything.
		for (String str : list) {
			if (args[args.length - 1].isEmpty() || str.startsWith(args[args.length - 1].toLowerCase())) {
				toReturn.add(str);
			}
		}
		
		return toReturn;
	}
}