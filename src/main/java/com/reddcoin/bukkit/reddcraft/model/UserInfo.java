package com.reddcoin.bukkit.reddcraft.model;

/**
 * UserInfo object-model
 * @author Leonard Simonse
 */
public class UserInfo {
	private String DateCreated;
	private String DepositAddress;
	private String Username;
	
	public UserInfo(String dateCreated, String depositAddress, String userName) {
		this.DateCreated = dateCreated;
		this.DepositAddress = depositAddress;
		this.Username = userName;
	}

	public String getDateCreated() {
		return DateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.DateCreated = dateCreated;
	}

	public String getDepositAddress() {
		return DepositAddress;
	}

	public void setDepositAddress(String depositAddress) {
		this.DepositAddress = depositAddress;
	}

	public String getUserName() {
		return Username;
	}

	public void setUserName(String userName) {
		this.Username = userName;
	}
}
