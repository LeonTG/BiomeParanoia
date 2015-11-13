package com.leontg77.biomeparanoia;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

/**
 * Biome utils class.
 * <p>
 * Used for checking if a biome should be shown in /bl and getting the biome color.
 * 
 * @author LeonTG77
 */
public class BiomeUtils {

	/**
	 * Get the color of the given biome.
	 * 
	 * @param biome the given biome.
	 * @return The biome color in string format.
	 */
	public static String biomeColor(Biome biome) {
		switch (biome) {
		case BEACH:
			return ChatColor.YELLOW.toString() + ChatColor.ITALIC;
		case BIRCH_FOREST:
			return ChatColor.YELLOW.toString() + ChatColor.BOLD;
		case BIRCH_FOREST_HILLS:
			return ChatColor.YELLOW.toString() + ChatColor.BOLD;
		case BIRCH_FOREST_HILLS_MOUNTAINS:
			return ChatColor.YELLOW.toString() + ChatColor.BOLD;
		case BIRCH_FOREST_MOUNTAINS:
			return ChatColor.YELLOW.toString() + ChatColor.BOLD;
		case COLD_BEACH:
			return ChatColor.YELLOW.toString() + ChatColor.ITALIC;
		case COLD_TAIGA:
			return ChatColor.BLUE.toString();
		case COLD_TAIGA_HILLS:
			return ChatColor.BLUE.toString();
		case COLD_TAIGA_MOUNTAINS:
			return ChatColor.BLUE.toString();
		case DEEP_OCEAN:
			return ChatColor.DARK_BLUE.toString();
		case DESERT:
			return ChatColor.YELLOW.toString();
		case DESERT_HILLS:
			return ChatColor.YELLOW.toString();
		case DESERT_MOUNTAINS:
			return ChatColor.YELLOW.toString();
		case EXTREME_HILLS:
			return ChatColor.GRAY.toString();
		case EXTREME_HILLS_MOUNTAINS:
			return ChatColor.GRAY.toString();
		case EXTREME_HILLS_PLUS:
			return ChatColor.GRAY.toString();
		case EXTREME_HILLS_PLUS_MOUNTAINS:
			return ChatColor.GRAY.toString();
		case FLOWER_FOREST:
			return ChatColor.DARK_GREEN.toString();
		case FOREST:
			return ChatColor.DARK_GREEN.toString();
		case FOREST_HILLS:
			return ChatColor.DARK_GREEN.toString();
		case FROZEN_OCEAN:
			return ChatColor.DARK_BLUE.toString();
		case FROZEN_RIVER:
			return ChatColor.AQUA.toString();
		case HELL:
			return ChatColor.RED.toString();
		case ICE_MOUNTAINS:
			return ChatColor.WHITE.toString();
		case ICE_PLAINS:
			return ChatColor.WHITE.toString();
		case ICE_PLAINS_SPIKES:
			return ChatColor.WHITE.toString() + ChatColor.ITALIC;
		case JUNGLE:
			return ChatColor.GREEN.toString() + ChatColor.BOLD;
		case JUNGLE_EDGE:
			return ChatColor.GREEN.toString() + ChatColor.BOLD;
		case JUNGLE_EDGE_MOUNTAINS:
			return ChatColor.GREEN.toString() + ChatColor.BOLD;
		case JUNGLE_HILLS:
			return ChatColor.GREEN.toString() + ChatColor.BOLD;
		case JUNGLE_MOUNTAINS:
			return ChatColor.GREEN.toString() + ChatColor.BOLD;
		case MEGA_SPRUCE_TAIGA:
			return ChatColor.BLUE.toString() + ChatColor.BOLD;
		case MEGA_SPRUCE_TAIGA_HILLS:
			return ChatColor.BLUE.toString() + ChatColor.BOLD;
		case MEGA_TAIGA:
			return ChatColor.BLUE.toString() + ChatColor.BOLD;
		case MEGA_TAIGA_HILLS:
			return ChatColor.BLUE.toString() + ChatColor.BOLD;
		case MESA:
			return ChatColor.DARK_RED.toString();
		case MESA_BRYCE:
			return ChatColor.DARK_RED.toString();
		case MESA_PLATEAU:
			return ChatColor.DARK_RED.toString();
		case MESA_PLATEAU_FOREST:
			return ChatColor.DARK_RED.toString();
		case MESA_PLATEAU_FOREST_MOUNTAINS:
			return ChatColor.DARK_RED.toString();
		case MESA_PLATEAU_MOUNTAINS:
			return ChatColor.DARK_RED.toString();
		case MUSHROOM_ISLAND:
			return ChatColor.GRAY.toString() + ChatColor.ITALIC;
		case MUSHROOM_SHORE:
			return ChatColor.GRAY.toString() + ChatColor.ITALIC;
		case OCEAN:
			return ChatColor.DARK_BLUE.toString();
		case PLAINS:
			return ChatColor.GREEN.toString();
		case RIVER:
			return ChatColor.AQUA.toString();
		case ROOFED_FOREST:
			return ChatColor.DARK_GREEN.toString() + ChatColor.BOLD;
		case ROOFED_FOREST_MOUNTAINS:
			return ChatColor.DARK_GREEN.toString() + ChatColor.BOLD;
		case SAVANNA:
			return ChatColor.GOLD.toString();
		case SAVANNA_MOUNTAINS:
			return ChatColor.GOLD.toString();
		case SAVANNA_PLATEAU:
			return ChatColor.GOLD.toString();
		case SAVANNA_PLATEAU_MOUNTAINS:
			return ChatColor.GOLD.toString();
		case SKY:
			return ChatColor.BLACK.toString();
		case SMALL_MOUNTAINS:
			return ChatColor.GRAY.toString();
		case STONE_BEACH:
			return ChatColor.GRAY.toString();
		case SUNFLOWER_PLAINS:
			return ChatColor.GREEN.toString();
		case SWAMPLAND:
			return ChatColor.DARK_GRAY.toString();
		case SWAMPLAND_MOUNTAINS:
			return ChatColor.DARK_GRAY.toString();
		case TAIGA:
			return ChatColor.DARK_AQUA.toString();
		case TAIGA_HILLS:
			return ChatColor.DARK_AQUA.toString();
		case TAIGA_MOUNTAINS:
			return ChatColor.DARK_AQUA.toString();
		default:
			return null;
		}
	}

	/**
	 * Check if the given biome can be sent in a message.
	 * 
	 * @param biome the biome checking.
	 * @return <code>True</code> if the biome is sendable, <code>false</code> otherwise.
	 */
	public static boolean isSendable(Biome biome) {
		switch (biome) {
		case BEACH:
		case BIRCH_FOREST:
		case COLD_TAIGA:
		case DESERT:
		case EXTREME_HILLS:
		case FLOWER_FOREST:
		case FOREST:
		case HELL:
		case ICE_PLAINS:
		case ICE_PLAINS_SPIKES:
		case JUNGLE:
		case MEGA_TAIGA:
		case MESA:
		case MUSHROOM_ISLAND:
		case OCEAN:
		case PLAINS:
		case RIVER:
		case ROOFED_FOREST:
		case SAVANNA:
		case SKY:
		case SUNFLOWER_PLAINS:
		case SWAMPLAND:
		case TAIGA:
			return true;
		default:
			return false;
		}
	}
}