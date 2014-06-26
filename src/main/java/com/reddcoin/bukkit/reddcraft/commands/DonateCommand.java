package com.reddcoin.bukkit.reddcraft.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.reddcoin.bukkit.reddcraft.commands.base.BaseCommand;
import com.reddcoin.bukkit.reddcraft.reddapi.ReddAPI;
import com.reddcoin.bukkit.reddcraft.tasks.DonateTask;

/**
 * Handler for the /donate command.
 * @author Leonard Simonse
 */
public class DonateCommand extends BaseCommand {
	
	private String donationReceiver;
	
    public DonateCommand(String receiver, JavaPlugin plugin) {
    	super(plugin);
		donationReceiver = receiver;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] split) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;
        double amount;
        if (split.length == 1) {
        	try {
                amount = Double.valueOf(split[0].trim()).doubleValue();
                donate(player, amount);
             } catch (NumberFormatException nfe) {
                sendMessage(player, prefix + "Invalid amount: please try again");
             }
            return true;
        }
        else {
        	sendMessage(player, prefix + "Invalid syntax: please try again");
            return false;
        }
    }
    
    private void donate(Player player, double amount) {
    	if(donationReceiver != null) {
    	ReddAPI ReddAPI = com.reddcoin.bukkit.reddcraft.reddapi.ReddAPI.getInstance();
    	DonateTask donateTask = new DonateTask(this, ReddAPI, player, donationReceiver, amount);
    	donateTask.runTaskAsynchronously(thisPlugin);
    	}
    	else {
    		sendMessage(player, prefix + "Donation-receiver not set");
    	}
    }
}
