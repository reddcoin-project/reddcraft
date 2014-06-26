package com.reddcoin.bukkit.reddcraft.tasks;

import java.util.Arrays;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.reddcoin.bukkit.reddcraft.commands.DonateCommand;
import com.reddcoin.bukkit.reddcraft.reddapi.ReddAPI;
import com.reddcoin.bukkit.reddcraft.tasks.base.BaseTask;
import com.reddcoin.bukkit.uuidfetcher.UUIDFetcher;

/**
 * Asynchronous task for the /donate command.
 * @author Leonard Simonse
 */
public class DonateTask extends BaseTask {
	
	private DonateCommand command;
    private String playerToName;
	private UUID playerToUUID = null;
    private Double amount;
	
	public DonateTask(DonateCommand command, ReddAPI api, Player playerFrom, String playerToName, Double amount) {
        super(api, playerFrom);
		this.command = command;
        this.playerToName = playerToName;
        this.amount = amount;
    }
	
	@Override
    public void run() {
		UUIDFetcher fetcher = new UUIDFetcher(Arrays.asList(playerToName));
		try {
			playerToUUID = fetcher.getUUIDOf(playerToName);
		} catch (Exception e) {
			command.sendMessage(player, prefix
					+ "Cannot fetch this player: please try again");
			return;
		}
		if (playerToUUID != null) {
    	String infoString = api.moveToUser(player.getUniqueId().toString(), playerToUUID.toString(), amount);
    	if (!(checkForError(infoString))) {
    		command.sendMessage(player, prefix + "Thank you! Your donation of " +amount+ " RDD to this server has successfully been collected by " + playerToName);
    		Player playerTo = Bukkit.getServer().getPlayer(playerToUUID);
    		if(playerTo != null) {
    		command.sendMessage(playerTo, prefix + "You have received a donation of " +amount+ " RDD from " + player.getName());
    		}
    	} else {
    		command.sendMessage(player, prefix + getErrorFromJSON(infoString));
    	}
		}
		else {
			command.sendMessage(player, prefix
					+ "Unknown receiver: contact the admin");
		}
    }
}

