package com.reddcoin.bukkit.reddcraft.tasks;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.reddcoin.bukkit.reddcraft.commands.BalanceCommand;
import com.reddcoin.bukkit.reddcraft.reddapi.ReddAPI;
import com.reddcoin.bukkit.reddcraft.tasks.base.BaseTask;

/**
 * Asynchronous task for the /balance command.
 * @author Leonard Simonse
 */
public class BalanceTask extends BaseTask {
 
    private BalanceCommand command;
 
    public BalanceTask(BalanceCommand command, ReddAPI api, Player player) {
        super(api, player);
        this.command = command;
    }
 
    @Override
    public void run() {
    	String infoString = api.getUserBalanceDetail(player.getUniqueId().toString());
    	if (!(checkForError(infoString))) {
    		JsonElement element = gson.fromJson(infoString, JsonElement.class);
    		JsonObject jsonObj = element.getAsJsonObject();
    		String confirmedBalance =  jsonObj.get("ConfirmedBalance").getAsString();
    		String unconfirmedBalance =  jsonObj.get("PendingDeposits").getAsString();
    		command.sendMessage(player, ChatColor.RED + "[ReddCraft] " + ChatColor.RESET + "--Your Reddcoin-balance--");
    		command.sendMessage(player, "Confirmed:    " + confirmedBalance + " RDD");
    		command.sendMessage(player, "Unconfirmed: " + unconfirmedBalance + " RDD");
		} else {
			command.sendMessage(player, prefix + getErrorFromJSON(infoString));
		}
    }
}