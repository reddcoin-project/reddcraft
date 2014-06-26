package com.reddcoin.bukkit.reddcraft.commands.base;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Baseclass for our commands.
 * @author Leonard Simonse
 */
public class BaseCommand implements CommandExecutor {
	
	protected String prefix = ChatColor.RED + "[ReddCraft] " + ChatColor.RESET;
	protected JavaPlugin thisPlugin;
	
	public BaseCommand(JavaPlugin plugin) {
		thisPlugin = plugin;
	}
    
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
			String[] arg3) {
		return false;
	}
	
	public void sendMessage(Player receiver, String message) {
		receiver.sendMessage(message);
	}
}
