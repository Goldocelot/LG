package be.goldocelot.lg;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import be.goldocelot.lg.role.RoleCmd;
import be.goldocelot.lg.role.RoleConfig;
import be.goldocelot.lg.role.RoleGuiEvent;
import be.goldocelot.lg.role.RoleManager;


public class Main extends JavaPlugin{

	private RoleConfig rConfig;
	private RoleManager rManager;
	
	@Override
	public void onEnable() {
	
		this.rConfig = new RoleConfig(this);
		this.rConfig.initFolder();
		this.rConfig.initFile();
		this.rConfig.initConfig();
		
		this.rManager = new RoleManager(rConfig);
		
		Bukkit.getPluginManager().registerEvents(new RoleGuiEvent(rConfig, rManager), this);
		getCommand("composition").setExecutor(new RoleCmd(rManager));;
		
	}
	
	@Override
	public void onDisable() {
		
		
	}
	
}
