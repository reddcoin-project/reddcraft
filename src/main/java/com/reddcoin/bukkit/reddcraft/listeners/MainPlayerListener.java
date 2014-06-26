package com.reddcoin.bukkit.reddcraft.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.reddcoin.bukkit.reddcraft.ReddCraftPlugin;
import com.reddcoin.bukkit.reddcraft.reddapi.ReddAPI;
import com.reddcoin.bukkit.reddcraft.tasks.CreateNewUserTask;

/**
 * Handle events for all Player related events
 * @author Leonard Simonse
 */
public class MainPlayerListener implements Listener {
    private final ReddCraftPlugin plugin;

    public MainPlayerListener(ReddCraftPlugin instance) {
        plugin = instance;
    }
    
    public boolean checkForError(String jsonString) {
		if (jsonString.contains("ErrorMessage")) {
			return true;
		}
		return false;
	}

	public String getErrorFromJSON(String jsonString) {
		Gson gson = new Gson();
		JsonElement element = gson.fromJson(jsonString, JsonElement.class);
		JsonObject jsonObj = element.getAsJsonObject();
		return jsonObj.get("ErrorMessage").getAsString();
	}
	
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ReddAPI ReddAPI = com.reddcoin.bukkit.reddcraft.reddapi.ReddAPI.getInstance();
        CreateNewUserTask createNewUserTask = new CreateNewUserTask(this, ReddAPI, player);
    	createNewUserTask.runTaskAsynchronously(plugin);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
    	
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
    	
    }
    
    public void sendMessage(Player receiver, String message) {
		receiver.sendMessage(message);
	}
}
