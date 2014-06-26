package com.reddcoin.bukkit.reddcraft.tasks.base;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.reddcoin.bukkit.reddcraft.reddapi.ReddAPI;

/**
 * Baseclass for our asynchronous tasks.
 * @author Leonard Simonse
 */
public class BaseTask extends BukkitRunnable {

    protected ReddAPI api;
    protected Player player;
    protected String prefix = ChatColor.RED + "[ReddCraft] " + ChatColor.RESET;
    protected Gson gson = new Gson();
 
    public BaseTask(ReddAPI api, Player player) {
        this.api = api;
        this.player = player;
    }
 
    @Override
    public void run() {
    	
    }
    
	public boolean checkForError(String jsonString) {
		if (jsonString.contains("ErrorMessage")) {
			return true;
		}
		return false;
	}

	public String getErrorFromJSON(String jsonString) {
		JsonElement element = gson.fromJson(jsonString, JsonElement.class);
		JsonObject jsonObj = element.getAsJsonObject();
		return jsonObj.get("ErrorMessage").getAsString();
	}
 
}