package be.goldocelot.lg.role;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import be.goldocelot.lg.Main;

/**
 * 
 * @author Nicolas Gerard (Goldocelot)
 * Class qui permet la bonne gestion du dossier de config du jeu
 */
public class RoleConfig {

	private Main main;
	private File file;
	
	public RoleConfig(Main main) {
		this.main = main;
	}
	
	// M�thode qui cr�� le dossier si celui-ci n'existe pas d�ja
	public void initFolder() {
		if(!this.main.getDataFolder().exists()) this.main.getDataFolder().mkdirs();
	}
	
	//  M�thode qui cr�e le fichier si celui-ci n'existe pas
	public void initFile() {
		this.file = new File(this.main.getDataFolder(), "role.yml");
		
		if(!this.file.exists()) {
			try {
				this.file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// M�thode qui supprime le fichi� si celui-ci existe
	public void deleteFile() {
		this.file = new File(this.main.getDataFolder(), "role.yml");
		
		if(this.file.exists()) {
			this.file.delete();
		}
	}
	
	// M�thode qui permet d'acc�der au fichier
	public File getFile() {
		return this.file;
	}
	
	// M�thode qui permet de charg� un fichier pour le modifier
	public YamlConfiguration getNewConfiguration() {
		YamlConfiguration config = new YamlConfiguration();
		
		try {
			config.load(this.file);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		} 
		return config;
	}
	
	// M�thode qui �crit les configurations de base dans le fichier si des lignes on �t� supprim� ou si c'est le premier lancement
	public void initConfig() {
		YamlConfiguration config = this.getNewConfiguration();
		if(!config.contains("Role.Simples Villageois")) config.set("Role.Simples Villageois", 0);
		if(!config.contains("Role.Automatique")) config.set("Role.Automatique", true);
		if(!config.contains("Role.Sorci�re")) config.set("Role.Sorci�re", false);
		if(!config.contains("Role.Voyante")) config.set("Role.Voyante", false);
		if(!config.contains("Role.Cupidon")) config.set("Role.Cupidon", false);
		if(!config.contains("Role.Petite fille")) config.set("Role.Petite fille", false);
		if(!config.contains("Role.Voleur")) config.set("Role.Voleur", false);
		if(!config.contains("Role.Chasseur")) config.set("Role.Chasseur", false);
		if(!config.contains("Role.Loups-Garous")) config.set("Role.Loups-Garous", 1);
		
		try {
			config.save(this.file);
		} catch (IOException e) {
		 	e.printStackTrace();
		}
	}
}