package com.reddcoin.bukkit.reddcraft.tasks;

import org.bukkit.entity.Player;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.reddcoin.bukkit.reddcraft.commands.AccountCommand;
import com.reddcoin.bukkit.reddcraft.model.UserInfo;
import com.reddcoin.bukkit.reddcraft.reddapi.ReddAPI;
import com.reddcoin.bukkit.reddcraft.tasks.base.BaseTask;

/**
 * Asynchronous task for the /info command.
 * @author Leonard Simonse
 */
public class AccountTask extends BaseTask {
 
    private AccountCommand command;
 
    public AccountTask(AccountCommand command, ReddAPI api, Player player) {
    	super(api, player);
        this.command = command;
    }
 
    @Override
    public void run() {
    	String playerID = player.getUniqueId().toString();
    	String infoString = api.getUserInfo(playerID);
    	if (!(checkForError(infoString))) {
    		command.sendMessage(player, prefix + "--Your account-info--");
    		UserInfo currentUserInfo = getUserInfoFromJSON(infoString);
    		command.sendMessage(player, prefix + "Username: " + currentUserInfo.getUserName());
    		command.sendMessage(player, prefix + "Deposit-address: ");
    		command.dispatchCommand("tellraw " + player.getName() + " {\"text\":\"\",\"extra\":[{\"text\":\""+currentUserInfo.getDepositAddress()+"\",\"color\":\"white\",\"italic\":\"true\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\""+currentUserInfo.getDepositAddress()+"\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"§7This is your deposit-address, click it and use Ctrl+A and Ctrl+C to copy it\"}}]}");
		} else {
			command.sendMessage(player, prefix + getErrorFromJSON(infoString));
		}
    }
    
    private UserInfo getUserInfoFromJSON(String jsonString) {
		JsonParser parser = new JsonParser();
		JsonObject obj = parser.parse(jsonString).getAsJsonObject();
		UserInfo element = gson.fromJson(obj, UserInfo.class);
		return element;
	}
}