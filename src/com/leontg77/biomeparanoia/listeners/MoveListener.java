package com.leontg77.biomeparanoia.listeners;

import org.bukkit.GameMode;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.leontg77.biomeparanoia.BiomeUtils;

/**
 * Move listener
 * <p> 
 * Class used to listen for the player move event to change their color if they're in a new biome.
 * 
 * @author LeonTG77
 */
public class MoveListener implements Listener {

	@EventHandler(ignoreCancelled = true)
	public void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		Biome biome = player.getLocation().getBlock().getBiome();
		 
		try {
			// if their gamemode is spectator mode, we don't want to set a color.
			if (player.getGameMode() == GameMode.SPECTATOR) {
				// setting to null to reset it incase they had a color.
				player.setPlayerListName(null);
				return;
			}
		} catch (Exception e) {
			// The SPECTATOR enum wasn't found that means they are not using 1.8+
		}
		
		// set their tab name to the biome color.
		player.setPlayerListName(BiomeUtils.biomeColor(biome) + player.getName());
	}
}