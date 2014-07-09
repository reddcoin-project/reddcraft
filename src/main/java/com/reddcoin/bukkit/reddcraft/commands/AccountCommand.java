package com.reddcoin.bukkit.reddcraft.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.reddcoin.bukkit.reddcraft.commands.base.BaseCommand;
import com.reddcoin.bukkit.reddcraft.reddapi.ReddAPI;
import com.reddcoin.bukkit.reddcraft.tasks.AccountTask;

/**
 * Handler for the /info command.
 * @author Leonard Simonse
 */
public class AccountCommand extends BaseCommand {
	
	public AccountCommand(JavaPlugin plugin) {
		super(plugin);
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] split) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;
        if (split.length == 0) {
        	getInfo(player);
            return true;
        }
        else {
        	sendMessage(player, prefix + "Invalid syntax: please try again");
            return false;
        }
    }
	
	public void dispatchCommand(String command) {
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
	}
    
    private void getInfo(Player player) {
    	ReddAPI ReddAPI = com.reddcoin.bukkit.reddcraft.reddapi.ReddAPI.getInstance();
    	AccountTask infoTask = new AccountTask(this, ReddAPI, player);
    	infoTask.runTaskAsynchronously(thisPlugin);
    }
}
