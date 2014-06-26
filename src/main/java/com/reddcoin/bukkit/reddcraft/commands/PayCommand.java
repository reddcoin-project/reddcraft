package com.reddcoin.bukkit.reddcraft.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.reddcoin.bukkit.reddcraft.commands.base.BaseCommand;
import com.reddcoin.bukkit.reddcraft.reddapi.ReddAPI;
import com.reddcoin.bukkit.reddcraft.tasks.PayTask;

/**
 * Handler for the /pay command.
 * @author Leonard Simonse
 */
public class PayCommand extends BaseCommand {
	
	public PayCommand(JavaPlugin plugin) {
		super(plugin);
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] split) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;
        double amount;
        if (split.length == 2) {
        	try {
                amount = Double.valueOf(split[1].trim()).doubleValue();
                pay(player, split[0] , amount);
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
    
    private void pay(Player player, String userNameTo, double amount) {
    	ReddAPI ReddAPI = com.reddcoin.bukkit.reddcraft.reddapi.ReddAPI.getInstance();
    	PayTask payTask = new PayTask(this, ReddAPI, player, userNameTo, amount);
    	payTask.runTaskAsynchronously(thisPlugin);
    }
}
