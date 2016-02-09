package com.leontg77.biomeparanoia.listeners;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.leontg77.biomeparanoia.Utils;

/**
 * Move listener
 * <p> 
 * Class used to listen for the player move event to change their color if they're in a new biome.
 * 
 * @author LeonTG77
 */
public class MoveListener implements Listener {

	@EventHandler(ignoreCancelled = true)
	public void on(final PlayerMoveEvent event) {
		final Location from = event.getFrom();
		final Location to = event.getTo();
			
		// if they move their head or pixels on the block I don't care, I only care if its a new block.
		if (from.getBlockX() == to.getBlockX() || from.getBlockZ() == to.getBlockZ()) {
			return;
		}
		
		final Player player = event.getPlayer();
		final Biome biome = to.getBlock().getBiome();
		
		// no need to set it if its the same biome, that would just lag.
		if (from.getBlock().getBiome().equals(biome)) {
			return;
		}
		
		final String biomeColor = Utils.getBiomeColor(biome);
		final String name = player.getName();
		 
		try {
			// if their gamemode is spectator mode, we don't want to set a color.
			if (player.getGameMode() == GameMode.SPECTATOR) {
				// setting to null to reset it incase they had a color.
				player.setPlayerListName(null);
				return;
			}
		} catch (Exception e) {
			// The SPECTATOR enum wasn't found that means they are not using 1.8+
			// 1.7 or lower didnt support 16+ caracter long names.
			player.setPlayerListName(biomeColor + name.substring(0, Math.min(name.length(), 16 - biomeColor.length())));
			return;
		}
		
		player.setPlayerListName(biomeColor + name);
	}
}