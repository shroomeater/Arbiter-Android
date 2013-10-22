package com.lmn.Arbiter_Android.ListItems;

public class ServerListItem {
	private int id;
	private String serverName;
	private String url;
	private String username;
	private String password;
	
	public ServerListItem(String serverName, String url, 
			String username, String password, int id){
		this.serverName = serverName;
		this.url = url;
		this.username = username;
		this.password = password;
		this.id = id;
	}
	
	public String getServerName(){
		return serverName;
	}
	
	public String getUrl(){
		return url;
	}
	
	public String getUsername(){
		return username;
	}
	
	public String getPassword(){
		return password;
	}
	
	public int getId(){
		return id;
	}
}
