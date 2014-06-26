
package com.reddcoin.bukkit.reddcraft;

import java.util.HashMap;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.reddcoin.bukkit.reddcraft.commands.BalanceCommand;
import com.reddcoin.bukkit.reddcraft.commands.DonateCommand;
import com.reddcoin.bukkit.reddcraft.commands.InfoCommand;
import com.reddcoin.bukkit.reddcraft.commands.PayCommand;
import com.reddcoin.bukkit.reddcraft.commands.WithdrawCommand;
import com.reddcoin.bukkit.reddcraft.listeners.MainPlayerListener;
import com.reddcoin.bukkit.reddcraft.reddapi.ReddAPI;

/**
 * Reddcoin plugin for Bukkit
 *
 * @author Leonard Simonse
 */
public class ReddCraftPlugin extends JavaPlugin {
    private final MainPlayerListener playerListener = new MainPlayerListener(this);
    private final HashMap<Player, Boolean> debugees = new HashMap<Player, Boolean>();

    @Override
    public void onDisable() {
        getLogger().info("Disabling ReddCraft");
    }

    @Override
    public void onEnable() {
    	this.saveDefaultConfig();

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(playerListener, this);
        
        ReddAPI ReddAPI = com.reddcoin.bukkit.reddcraft.reddapi.ReddAPI.getInstance();
    	ReddAPI.setKey_GET(ReddCraftPlugin.this.getConfig().getString("reddapigetkey"));
    	ReddAPI.setKey_POST(ReddCraftPlugin.this.getConfig().getString("reddapipostkey"));

        getCommand("balance").setExecutor(new BalanceCommand(this));
        getCommand("donate").setExecutor(new DonateCommand(ReddCraftPlugin.this.getConfig().getString("donationreceiver"), this));
        getCommand("info").setExecutor(new InfoCommand(this));
        getCommand("pay").setExecutor(new PayCommand(this));
        getCommand("tip").setExecutor(new PayCommand(this));
        getCommand("withdraw").setExecutor(new WithdrawCommand(this));

        PluginDescriptionFile pdfFile = this.getDescription();
        getLogger().info( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );
    }

    public boolean isDebugging(final Player player) {
        if (debugees.containsKey(player)) {
            return debugees.get(player);
        } else {
            return false;
        }
    }

    public void setDebugging(final Player player, final boolean value) {
        debugees.put(player, value);
    }
}
