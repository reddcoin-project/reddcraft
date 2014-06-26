package com.reddcoin.bukkit.reddcraft.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.reddcoin.bukkit.reddcraft.commands.base.BaseCommand;
import com.reddcoin.bukkit.reddcraft.reddapi.ReddAPI;
import com.reddcoin.bukkit.reddcraft.tasks.BalanceTask;

/**
 * Handler for the /balance command.
 * @author Leonard Simonse
 */
public class BalanceCommand extends BaseCommand {
	
	public BalanceCommand(JavaPlugin plugin) {
		super(plugin);
	}
	
    public boolean onCommand(CommandSender sender, Command command, String label, String[] split) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;
        if (split.length == 0) {
            echoBalance(player);
            return true;
        } else {
        	sendMessage(player, prefix + "Invalid syntax: please try again");
            return false;
        }
    }
    
    private void echoBalance(Player player) {
    	ReddAPI ReddAPI = com.reddcoin.bukkit.reddcraft.reddapi.ReddAPI.getInstance();
    	BalanceTask balanceTask = new BalanceTask(this, ReddAPI, player);
    	balanceTask.runTaskAsynchronously(thisPlugin);
    }
}
