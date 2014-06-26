package com.reddcoin.bukkit.reddcraft.tasks;

import org.bukkit.entity.Player;

import com.reddcoin.bukkit.reddcraft.listeners.MainPlayerListener;
import com.reddcoin.bukkit.reddcraft.reddapi.ReddAPI;
import com.reddcoin.bukkit.reddcraft.tasks.base.BaseTask;

/**
 * Asynchronous task to create a new user.
 * @author Leonard Simonse
 */
public class CreateNewUserTask extends BaseTask {

	private MainPlayerListener listener;

	public CreateNewUserTask(MainPlayerListener listener, ReddAPI api,
			Player player) {
		super(api, player);
		this.listener = listener;
	}

	@Override
	public void run() {
		String playerID = player.getUniqueId().toString();
		String infoString = api.getUserBalance(playerID);
		if (!(checkForError(infoString))) {
			// Do nothing
		} else {
			listener.sendMessage(player, prefix + "Creating your ReddCraft-account");
			api.createNewUser(playerID);
			listener.sendMessage(player, prefix + "ReddCraft-Account created");
			listener.sendMessage(player, prefix + "use '/help ReddCraft' to get started");
		}
	}
}