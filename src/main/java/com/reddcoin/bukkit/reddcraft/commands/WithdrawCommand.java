package com.reddcoin.bukkit.reddcraft.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.reddcoin.bukkit.reddcraft.commands.base.BaseCommand;
import com.reddcoin.bukkit.reddcraft.reddapi.ReddAPI;
import com.reddcoin.bukkit.reddcraft.tasks.WithdrawTask;

/**
 * Handler for the /withdraw command.
 * @author Leonard Simonse
 */
public class WithdrawCommand extends BaseCommand {
	
	public WithdrawCommand(JavaPlugin plugin) {
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
                withdraw(player, split[0] , amount);
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
    
    private void withdraw(Player player, String addressTo, double amount) {
    	ReddAPI ReddAPI = com.reddcoin.bukkit.reddcraft.reddapi.ReddAPI.getInstance();
    	WithdrawTask withdrawTask = new WithdrawTask(this, ReddAPI, player, addressTo, amount);
    	withdrawTask.runTaskAsynchronously(thisPlugin);
    }
    
	public void sendMessage(Player receiver, String message) {
		receiver.sendMessage(message);
	}
}
