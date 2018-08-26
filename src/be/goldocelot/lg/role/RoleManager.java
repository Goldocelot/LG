package be.goldocelot.lg.role;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import be.goldocelot.role.playable.Role;
import be.goldocelot.role.playable.SimpleVillageois;

public class RoleManager {

	private RoleConfig rConfig;
	
	public RoleManager(RoleConfig rConfig) {
		this.rConfig = rConfig;
	}
	
	public RoleEnum getRole(Player p) {
		YamlConfiguration config = rConfig.getNewConfiguration();
		if(config.getString("Player"+p.getName()).equals("Voleur")) return RoleEnum.VOLEUR;
		else if(config.getString("Player"+p.getName()).equals("Loup-Garou")) return RoleEnum.LOUP_GAROU;
		else if(config.getString("Player"+p.getName()).equals("Simple Villageois")) return RoleEnum.SIMPLE_VILLAGEOIS;
		else if(config.getString("Player"+p.getName()).equals("Chasseur")) return RoleEnum.CHASSEUR;
		else if(config.getString("Player"+p.getName()).equals("Sorcière")) return RoleEnum.SORCIÈRE;
		else if(config.getString("Player"+p.getName()).equals("Cupidon")) return RoleEnum.CUPIDON;
		else if(config.getString("Player"+p.getName()).equals("Petite fille")) return RoleEnum.PETITE_FILLE;
		else if(config.getString("Player"+p.getName()).equals("Voyante")) return RoleEnum.VOYANTE;
		else return null;
	}
	
	public boolean isRole(Player p, RoleEnum role) {
		return role.equals(this.getRole(p));
	}
	
	public void randomiser() {
		YamlConfiguration config = rConfig.getNewConfiguration();
		List<String> rRole = new ArrayList<String>();
		if(config.getBoolean("Role.Sorcière")) rRole.add("Sorcière");
		if(config.getBoolean("Role.Voyante")) rRole.add("Voyante");
		if(config.getBoolean("Role.Cupidon")) rRole.add("Cupidon");
		if(config.getBoolean("Role.Petite fille")) rRole.add("Petite fille");
		if(config.getBoolean("Role.Voleur")) rRole.add("Voleur");
		if(config.getBoolean("Role.Chasseur")) rRole.add("Chasseur");
		int a = 0;
		while(a < config.getInt("Role.Simples Villageois")) {
			a++;
			rRole.add("Simple Villageois");
		}
		a = 0;
		while(a < config.getInt("Role.Loups-Garous")) {
			a++;
			rRole.add("Loup-Garou");
		}
		if(config.getBoolean("Role.Voleur")) {
			int y = 0;
			while(y<2) {
				double z = Math.random()*(rRole.size()-1);
				int x = (int) Math.round(z);
				y++;
				config.set("Voleur."+y, rRole.get(x));
				rRole.remove(x);
			}			
		}
		for(Player onGame : Bukkit.getOnlinePlayers()) {
			double z = Math.random()*(rRole.size()-1);
			int x = (int) Math.round(z);
			config.set("Player."+onGame.getName(), rRole.get(x));
			rRole.remove(x);
			try {
				config.save(rConfig.getFile());
			} catch (IOException io) {
			 	io.printStackTrace();
			}
		}
	}
	
	public void initialiser() {
		YamlConfiguration config = rConfig.getNewConfiguration();
		if(config.getInt("Role.Simples Villageois") != 0) {
			Role SimpleVillageois = new SimpleVillageois(this.rConfig);
			SimpleVillageois.setupPlayer();
		}
	}
	
}
