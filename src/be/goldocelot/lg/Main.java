package be.goldocelot.lg;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import be.goldocelot.lg.role.RoleConfig;
import be.goldocelot.lg.role.RoleCmd;
import be.goldocelot.lg.role.RoleGuiEvent;
import be.goldocelot.lg.role.RoleGuiManager;
import be.goldocelot.lg.role.RoleManager;
import be.goldocelot.lg.role.playable.sorcière.SorcièreCmd;
import be.goldocelot.lg.role.playable.sorcière.SorcièreEvents;

/**
 * 
 * @author Nicolas Gerard (Goldocelot)
 *
 */
public class Main extends JavaPlugin{

	private RoleConfig rConfig;
	private RoleGuiManager rGManager;
	private RoleManager rMan;
	
	@Override
	public void onEnable() {
	
		Bukkit.getServer().getWorld("world").setGameRuleValue("keepInventory", "true");
		
		// Initialisation du fichier de configuration.
		rConfig = new RoleConfig(this);
		rConfig.initFolder();
		rConfig.initFile();
		rConfig.initConfig();
		
		// Initialisation du manager de role ainsi que de l'interface utilisateur.
		this.rGManager = new RoleGuiManager(rConfig);
		this.rMan = new RoleManager(rConfig);
		
		// Initialisation des événements et des commandes.
		getCommand("composition").setExecutor(new RoleCmd(rGManager));;
		SorcièreCmd sCmd = new SorcièreCmd(rMan);
		getCommand("vie").setExecutor(sCmd);
		Bukkit.getPluginManager().registerEvents(new SorcièreEvents(sCmd, rMan, this), this);
		Bukkit.getPluginManager().registerEvents(new RoleGuiEvent(rConfig, rGManager, rMan), this);
		
	}
	
	@Override
	public void onDisable() {	
		this.rConfig.deleteFile();	
	}
	
}
