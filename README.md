reddcraft
=========

Reddcoin plugin for Minecraft (Bukkit)

####Installing
* [Register at ReddAPI](https://www.reddapi.com/) (please enable 2FA!)
* [Generate a GET- and POST-key at ReddAPI](https://www.reddapi.com/Vault/APIKeys)
* [Download the latest release of this plugin](http://reddcoinhub.com/reddcraft/reddcraft-latest.jar) or compile this plugin using Maven
* Copy the .jar-file into the /plugins directory of your Bukkit-server
* Start the server
* Wait for the server to start completely
* Stop the server
* Edit /plugins/ReddCraft/config.yml to reflect your details
* Start the server

####Commands

```
/balance
        description: Displays your current ReddCoin-balance.
        usage: "Usage: '/balance' to display your current Reddcoin-balance"
        permission: reddcraft.balance
/donate
        description: Donate Reddcoins to this server.
        usage: "Usage: '/donate AMOUNT' to donate a certain of Reddcoins to this server"
        permission: reddcraft.donate
/info
        description: Displays your ReddCoin-address and its balance.
        usage: "Usage: '/info' to display your username and Reddcoin-address"
        permission: reddcraft.info
/pay
        description: Pay a player an amount of Reddcoins.
        usage: "Usage: '/pay PLAYERNAME AMOUNT' to pay a certain of Reddcoins to a specific player"
        permission: reddcraft.pay
/tip
        description: Tip a player an amount of Reddcoins.
        usage: "Usage: '/tip PLAYERNAME AMOUNT' to tip a certain of Reddcoins to a specific player"
        permission: reddcraft.tip
/withdraw
        description: (0.5% FEE) Withdraw Reddcoins from your ReddCraft-account.
        usage: "Usage: '/withdraw ADDRESS AMOUNT' to withdraw Reddcoins to a specific address"
        permission: reddcraft.withdraw
```
####Permissions
```
    reddcraft.*:
        description: Gives access to all ReddCraft commands
        children:
            reddcraft.balance: true
            reddcraft.donate: true
            reddcraft.info: true
            reddcraft.pay: true
            reddcraft.tip: true
            reddcraft.withdraw: true
    reddcraft.balance:
        description: Allows usage of /balance
        default: true
    reddcraft.donate:
        description: Allows usage of /donate
        default: true
    reddcraft.info:
        description: Allows usage of /info
        default: true
    reddcraft.pay:
        description: Allows usage of /pay
        default: true
    reddcraft.tip:
        description: Allows usage of /tip
        default: true
    reddcraft.withdraw:
        description: Allows usage of /withdraw
        default: true
```
