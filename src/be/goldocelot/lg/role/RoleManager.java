package be.goldocelot.lg.role;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import be.goldocelot.lg.role.playable.Chasseur;
import be.goldocelot.lg.role.playable.Cupidon;
import be.goldocelot.lg.role.playable.LoupGarou;
import be.goldocelot.lg.role.playable.PetiteFille;
import be.goldocelot.lg.role.playable.SimpleVillageois;
import be.goldocelot.lg.role.playable.Sorcière;
import be.goldocelot.lg.role.playable.Voleur;
import be.goldocelot.lg.role.playable.Voyante;

/**
 * 
 * @author Nicolas Gerard (Goldocelot)
 * Class qui permet de gérer les rôles des joueurs
 */
public class RoleManager {

	private RoleConfig rConfig;
	
	public RoleManager(RoleConfig rConfig) {
		this.rConfig = rConfig;
	}
	
	// Méthode qui permet d'avoir le rôle d'un joueur
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
	
	// Méthode qui permet de tester si un joueur posséde un rôle x
	public boolean isRole(Player p, RoleEnum role) {
		return role.equals(this.getRole(p));
	}
	
	// Méthode qui attribut alléatoirement les rôles
	public void randomiser() {
		YamlConfiguration config = rConfig.getNewConfiguration();
		List<String> rRole = new ArrayList<String>();
		// On ajoute tout les rôles activé et le bon nombre de loups-garous et villageois dans les rôles possibles
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
		// Si le voleur est activé on prend 2 rôle au hasard qui seront le rôle que pourra choisir le voleur
		if(config.getBoolean("Role.Voleur")) {
			int y = 0;
			while(y<2) {
				double z = Math.random()*(rRole.size()-1);
				int x = (int) Math.round(z);
				y++;
				config.set("Voleur."+y, rRole.get(x));
				rRole.remove(x);
				try {
					config.save(rConfig.getFile());
				} catch (IOException io) {
				 	io.printStackTrace();
				}
			}			
		}
		// On attribut les rôle restant aux joueurs de la partie
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
	
	// Méthodes qui permet d'initialiser la partie
	public void initialiser() {
		YamlConfiguration config = rConfig.getNewConfiguration();
		// On initialise le/les loup(s)-garou(s)
		Role LoupGarou = new LoupGarou(this.rConfig);
		LoupGarou.setupPlayer();
		// S'il y a au moins un villageois on le/les initialises
		if(config.getInt("Role.Simples Villageois") != 0) {
			Role SimpleVillageois = new SimpleVillageois(this.rConfig);
			SimpleVillageois.setupPlayer();
		}
		// S'il y a une sorcière on l'initialise
		if(config.getBoolean("Role.Sorcière")) {
			Role Sorcière = new Sorcière(this.rConfig);
			Sorcière.setupPlayer();
		}
		// S'il y a un cupidon on l'initialise
		if(config.getBoolean("Role.Cupidon")) {
			Role Cupidon = new Cupidon(this.rConfig);
			Cupidon.setupPlayer();
		}
		// S'il y a une voyante on l'initialise
		if(config.getBoolean("Role.Voyante")) {
			Role Voyante = new Voyante(this.rConfig);
			Voyante.setupPlayer();
		}
		// S'il y a un voleur on l'initialise
		if(config.getBoolean("Role.Voleur")) {
			Role Voleur = new Voleur(this.rConfig);
			Voleur.setupPlayer();
		}
		// S'il y a un chasseur on l'initialise
		if(config.getBoolean("Role.Chasseur")) {
			Role Chasseur = new Chasseur(this.rConfig);
			Chasseur.setupPlayer();
		}
		// S'il y a une petite fille on l'initialise
		if(config.getBoolean("Role.Petite fille")) {
			Role PetiteFille = new PetiteFille(this.rConfig);
			PetiteFille.setupPlayer();
		}
	}
	
}
