package com.leontg77.biomeparanoia;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;

import com.google.common.collect.ImmutableSet;

/**
 * Utilities class.
 * <p>
 * Has methods for getting the biome color and getting which biomes are displayed in /bl.
 * 
 * @author LeonTG77
 */
public class Utils {
	
	/**
	 * A set of sendable biomes to /bl.
	 */
	private static final Set<Biome> SENDABLE_BIOMES = ImmutableSet.of(
			Biome.BEACH, Biome.BIRCH_FOREST, Biome.COLD_TAIGA, Biome.DESERT,
			Biome.EXTREME_HILLS, Biome.FLOWER_FOREST, Biome.FOREST, Biome.HELL,
			Biome.ICE_PLAINS, Biome.ICE_PLAINS_SPIKES, Biome.JUNGLE, Biome.MESA,
			Biome.MEGA_TAIGA, Biome.MUSHROOM_ISLAND, Biome.OCEAN, Biome.PLAINS,
			Biome.RIVER, Biome.ROOFED_FOREST, Biome.SAVANNA, Biome.SKY,
			Biome.SUNFLOWER_PLAINS, Biome.SWAMPLAND, Biome.TAIGA
	);

	/**
	 * Check if the given biome can be sent in the /bl command.
	 * 
	 * @param biome the biome checking.
	 * @return <code>True</code> if the biome is sendable, <code>false</code> otherwise.
	 */
	public static boolean isSendable(final Biome biome) {
		return SENDABLE_BIOMES.contains(biome);
	}
	
	/**
	 * Broadcasts a message to everyone online.
	 * 
	 * @param message the message.
	 */
	public static void broadcast(String message) {
		for (Player online : Bukkit.getOnlinePlayers()) {
			online.sendMessage(message);
		}
		
		Bukkit.getLogger().info(message);
	}
	
	/**
	 * Fix the given text with making the first letter captializsed and the rest not.
	 * 
	 * @param text the text fixing.
	 * @param replaceUnderscore True to replace all _ with a space, false otherwise.
	 * @return The new fixed text.
	 */
	public static String capitalizeString(String text, boolean replaceUnderscore) {
		if (text.isEmpty()) {
			return text;
		}
		
		if (text.length() == 1) {
			return text.toUpperCase();
		}
		
		String toReturn = text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
		
		if (replaceUnderscore) {
			toReturn = toReturn.replace("_", " ");
		} 
		
		return toReturn;
	}
	
	/**
	 * Get the color of the given biome.
	 * 
	 * @param biome the given biome.
	 * @return The biome color in string format.
	 */
	public static String getBiomeColor(final Biome biome) {
		switch (biome) {
		case BIRCH_FOREST:
		case BIRCH_FOREST_HILLS:
		case BIRCH_FOREST_HILLS_MOUNTAINS:
		case BIRCH_FOREST_MOUNTAINS:
			return ChatColor.YELLOW.toString() + ChatColor.BOLD;
		case EXTREME_HILLS:
		case EXTREME_HILLS_MOUNTAINS:
		case EXTREME_HILLS_PLUS:
		case EXTREME_HILLS_PLUS_MOUNTAINS:
			return ChatColor.GRAY.toString();
		case JUNGLE:
		case JUNGLE_EDGE:
		case JUNGLE_EDGE_MOUNTAINS:
		case JUNGLE_HILLS:
		case JUNGLE_MOUNTAINS:
			return ChatColor.GREEN.toString() + ChatColor.BOLD;
		case MEGA_SPRUCE_TAIGA:
		case MEGA_SPRUCE_TAIGA_HILLS:
		case MEGA_TAIGA:
		case MEGA_TAIGA_HILLS:
			return ChatColor.BLUE.toString() + ChatColor.BOLD;
		case MESA:
		case MESA_BRYCE:
		case MESA_PLATEAU:
		case MESA_PLATEAU_FOREST:
		case MESA_PLATEAU_FOREST_MOUNTAINS:
		case MESA_PLATEAU_MOUNTAINS:
			return ChatColor.DARK_RED.toString();
		case SAVANNA:
		case SAVANNA_MOUNTAINS:
		case SAVANNA_PLATEAU:
		case SAVANNA_PLATEAU_MOUNTAINS:
			return ChatColor.GOLD.toString();
		case COLD_TAIGA:
		case COLD_TAIGA_HILLS:
		case COLD_TAIGA_MOUNTAINS:
			return ChatColor.BLUE.toString();
		case TAIGA:
		case TAIGA_HILLS:
		case TAIGA_MOUNTAINS:
			return ChatColor.DARK_AQUA.toString();
		case DEEP_OCEAN:
		case OCEAN:
		case FROZEN_OCEAN:
			return ChatColor.DARK_BLUE.toString();
		case DESERT:
		case DESERT_HILLS:
		case DESERT_MOUNTAINS:
			return ChatColor.YELLOW.toString();
		case FLOWER_FOREST:
		case FOREST:
		case FOREST_HILLS:
			return ChatColor.DARK_GREEN.toString();
		case BEACH:
		case COLD_BEACH:
			return ChatColor.YELLOW.toString();
		case RIVER:
		case FROZEN_RIVER:
			return ChatColor.AQUA.toString();
		case SMALL_MOUNTAINS:
		case STONE_BEACH:
			return ChatColor.GRAY.toString();
		case PLAINS:
		case SUNFLOWER_PLAINS:
			return ChatColor.GREEN.toString();
		case SWAMPLAND:
		case SWAMPLAND_MOUNTAINS:
			return ChatColor.DARK_GRAY.toString();
		case ROOFED_FOREST:
		case ROOFED_FOREST_MOUNTAINS:
			return ChatColor.DARK_GREEN.toString() + ChatColor.BOLD;
		case MUSHROOM_ISLAND:
		case MUSHROOM_SHORE:
			return ChatColor.GRAY.toString() + ChatColor.ITALIC;
		case ICE_MOUNTAINS:
		case ICE_PLAINS:
			return ChatColor.WHITE.toString();
		case ICE_PLAINS_SPIKES:
			return ChatColor.WHITE.toString() + ChatColor.ITALIC;
		case HELL:
			return ChatColor.RED.toString();
		case SKY:
			return ChatColor.BLACK.toString();
		default:
			return ChatColor.MAGIC.toString();
		}
	}
}