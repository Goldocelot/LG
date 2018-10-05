package be.goldocelot.lg;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import be.goldocelot.lg.role.RoleCmd;
import be.goldocelot.lg.role.RoleConfig;
import be.goldocelot.lg.role.RoleGuiEvent;
import be.goldocelot.lg.role.RoleGuiManager;
import be.goldocelot.lg.role.RoleManager;

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
	
		// Initialisation du fichier de configuration.
		this.rConfig = new RoleConfig(this);
		this.rConfig.initFolder();
		this.rConfig.initFile();
		this.rConfig.initConfig();
		
		// Initialisation du manager de role ainsi que de l'interface utilisateur.
		this.rGManager = new RoleGuiManager(rConfig);
		this.rMan = new RoleManager(rConfig);
		
		// Initialisation des événement et des commandes.
		Bukkit.getPluginManager().registerEvents(new RoleGuiEvent(rConfig, rGManager, rMan), this);
		getCommand("composition").setExecutor(new RoleCmd(rGManager));;
		
	}
	
	@Override
	public void onDisable() {	
		this.rConfig.deleteFile();	
	}
	
}
