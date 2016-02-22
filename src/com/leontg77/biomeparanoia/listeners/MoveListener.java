package com.leontg77.biomeparanoia.listeners;

import org.bukkit.Location;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.leontg77.biomeparanoia.Main;
import com.leontg77.biomeparanoia.Utils;

/**
 * Move listener
 * <p> 
 * Class used to listen for the player move event to change their color if they're in a new biome.
 * 
 * @author LeonTG77
 */
public class MoveListener implements Listener {
	private final Main plugin;
	
	public MoveListener(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler(ignoreCancelled = true)
	public void on(PlayerMoveEvent event) {
		Location from = event.getFrom();
		Location to = event.getTo();
			
		// if they move their head or pixels on the block I don't care, I only care if its a new block.
		if (from.getBlockX() == to.getBlockX() && from.getBlockZ() == to.getBlockZ()) {
			return;
		}
		
		Player player = event.getPlayer();
		Biome biome = to.getBlock().getBiome();
		
		// no need to set it if its the same biome, that would just lag.
		if (from.getBlock().getBiome().name().equals(biome.name())) {
			return;
		}
		
		Utils.updatePlayer(player, plugin);
	}
}