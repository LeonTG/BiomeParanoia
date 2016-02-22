package com.leontg77.biomeparanoia.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

/**
 * Gamemode listener
 * <p> 
 * Class used to listen for the player gamemode change event and take away their color if they're going to spectator mode.
 * 
 * @author LeonTG77
 */
public class GamemodeListener implements Listener {

	@EventHandler(ignoreCancelled = true)
	public void on(PlayerGameModeChangeEvent event) {
		GameMode mode = event.getNewGameMode();
		Player player = event.getPlayer();
		 
		try {
			if (mode == GameMode.SPECTATOR) {
				player.setPlayerListName(null);
				return;
			}
		} catch (Exception e) {
			return;
		}
	}
}