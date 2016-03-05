package com.leontg77.biomeparanoia;

import java.util.List;
import java.util.Set;

import com.google.common.base.Optional;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Biome;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

/**
 * Utilities class.
 * <p>
 * Has methods for getting the biome color and getting which biomes are displayed in /bl.
 * 
 * @author LeonTG77
 */
public class Utils {

	public static final Optional<GameMode> SPECTATOR_GAMEMODE_OPTIONAL;

	static {
		GameMode spectatorGameMode = null;
		try {
			spectatorGameMode = GameMode.valueOf("SPECTATOR");
		} catch (IllegalArgumentException ignored) {}
		SPECTATOR_GAMEMODE_OPTIONAL = Optional.fromNullable(spectatorGameMode);
	}

	/**
	 * A set of sendable biomes to /bl.
	 */
	public static final Set<Biome> SENDABLE_BIOMES = ImmutableSet.of(
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
	 * Update the given list of player's tablist color.
	 * 
	 * @param plugin The main class.
	 * @param players The list of players to update.
	 */
	public static void updatePlayers(Main plugin, List<Player> players, Location loc) {
		for (Player player : players) {
			updatePlayer(player, loc, plugin);
		}
	}
	
	/**
	 * Update the given player's tablist color.
	 * 
	 * @param player The player to update.
	 * @param plugin The main class.
	 */
	public static void updatePlayer(Player player, Location loc, Main plugin) {
		Biome biome = loc == null ? player.getLocation().getBlock().getBiome() : loc.getBlock().getBiome();

		String biomeColor = ChatColor.translateAlternateColorCodes('&', Utils.getBiomeColor(biome, plugin));
		String name = player.getName();
		String newPlayerListName = biomeColor + name;

		if (SPECTATOR_GAMEMODE_OPTIONAL.isPresent()) {
			// if their gamemode is spectator mode, we don't want to set a color.
			if (player.getGameMode() == SPECTATOR_GAMEMODE_OPTIONAL.get()) {
				return;
			}
		} else {
			// The SPECTATOR enum wasn't found that means they are not using 1.8+
			// 1.7 or lower didnt support 16+ caracter long names.
			if (newPlayerListName.length() > 16) {
				player.setPlayerListName(biomeColor + name.substring(0, Math.min(name.length(), 16 - biomeColor.length())));
			}
		}

		player.setPlayerListName(newPlayerListName);
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
	public static String getBiomeColor(final Biome biome, Main plugin) {
		FileConfiguration config = plugin.getConfig();
		
		switch (biome) {
		case BIRCH_FOREST:
		case BIRCH_FOREST_HILLS:
		case BIRCH_FOREST_HILLS_MOUNTAINS:
		case BIRCH_FOREST_MOUNTAINS:
			return config.getString("birch_forest", "&e&l");
		case EXTREME_HILLS:
		case EXTREME_HILLS_MOUNTAINS:
		case EXTREME_HILLS_PLUS:
		case EXTREME_HILLS_PLUS_MOUNTAINS:
		case SMALL_MOUNTAINS:
		case STONE_BEACH:
			return config.getString("extreme_hills", "&7");
		case JUNGLE:
		case JUNGLE_EDGE:
		case JUNGLE_EDGE_MOUNTAINS:
		case JUNGLE_HILLS:
		case JUNGLE_MOUNTAINS:
			return config.getString("jungle", "&a&l");
		case MEGA_SPRUCE_TAIGA:
		case MEGA_SPRUCE_TAIGA_HILLS:
		case MEGA_TAIGA:
		case MEGA_TAIGA_HILLS:
			return config.getString("mega_taiga", "&3&l");
		case MESA:
		case MESA_BRYCE:
		case MESA_PLATEAU:
		case MESA_PLATEAU_FOREST:
		case MESA_PLATEAU_FOREST_MOUNTAINS:
		case MESA_PLATEAU_MOUNTAINS:
			return config.getString("mesa", "&4");
		case SAVANNA:
		case SAVANNA_MOUNTAINS:
		case SAVANNA_PLATEAU:
		case SAVANNA_PLATEAU_MOUNTAINS:
			return config.getString("savanna", "&6");
		case COLD_TAIGA:
		case COLD_TAIGA_HILLS:
		case COLD_TAIGA_MOUNTAINS:
			return config.getString("cold_taiga", "&9");
		case TAIGA:
		case TAIGA_HILLS:
		case TAIGA_MOUNTAINS:
			return config.getString("taiga", "&3");
		case DEEP_OCEAN:
		case OCEAN:
		case FROZEN_OCEAN:
			return config.getString("ocean", "&1");
		case DESERT:
		case DESERT_HILLS:
		case DESERT_MOUNTAINS:
			return config.getString("desert", "&e");
		case FLOWER_FOREST:
		case FOREST:
		case FOREST_HILLS:
			return config.getString("forest", "&2");
		case BEACH:
		case COLD_BEACH:
			return config.getString("beach", "&e&o");
		case RIVER:
		case FROZEN_RIVER:
			return config.getString("river", "&b");
		case PLAINS:
		case SUNFLOWER_PLAINS:
			return config.getString("plains", "&a");
		case SWAMPLAND:
		case SWAMPLAND_MOUNTAINS:
			return config.getString("swampland", "&8");
		case ROOFED_FOREST:
		case ROOFED_FOREST_MOUNTAINS:
			return config.getString("roofed_forest", "&2&l");
		case MUSHROOM_ISLAND:
		case MUSHROOM_SHORE:
			return config.getString("mushroom_island", "&7&o");
		case ICE_MOUNTAINS:
		case ICE_PLAINS:
			return config.getString("ice_plains", "&f");
		case ICE_PLAINS_SPIKES:
			return config.getString("ice_plains_spikes", "&f&o");
		case HELL:
			return config.getString("hell", "&c");
		case SKY:
			return config.getString("sky", "&0");
		default:
			return config.getString(biome.name().toLowerCase(), "&f");
		}
	}
}
