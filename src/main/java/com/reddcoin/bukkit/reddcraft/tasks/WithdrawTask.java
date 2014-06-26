package com.reddcoin.bukkit.reddcraft.tasks;

import org.bukkit.entity.Player;

import com.reddcoin.bukkit.reddcraft.commands.WithdrawCommand;
import com.reddcoin.bukkit.reddcraft.reddapi.ReddAPI;
import com.reddcoin.bukkit.reddcraft.tasks.base.BaseTask;

/**
 * Asynchronous task for the /withdraw command.
 * @author Leonard Simonse
 */
public class WithdrawTask extends BaseTask {
 
    private WithdrawCommand command;
    private String addressTo;
    private Double amount;
    
    public WithdrawTask(WithdrawCommand command, ReddAPI api, Player player, String addressTo, Double amount) {
    	super(api, player);
        this.command = command;
        this.addressTo = addressTo;
        this.amount = amount;
    }
 
    @Override
    public void run() {
    	String playerID = player.getUniqueId().toString();
    	String infoString = api.sendToAddress(playerID, addressTo, amount);
    	if (!(checkForError(infoString))) {
    		command.sendMessage(player, prefix + "Your withdrawal of " + amount + " RDD to " + addressTo + " was successful");
    		command.sendMessage(player, prefix + "Transaction-ID: " + infoString);
		} else {
			command.sendMessage(player, prefix + getErrorFromJSON(infoString));
		}
    } 
}