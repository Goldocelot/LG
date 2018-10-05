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
import be.goldocelot.lg.role.playable.Sorci�re;
import be.goldocelot.lg.role.playable.Voleur;
import be.goldocelot.lg.role.playable.Voyante;

/**
 * 
 * @author Nicolas Gerard (Goldocelot)
 * Class qui permet de g�rer les r�les des joueurs
 */
public class RoleManager {

	private RoleConfig rConfig;
	
	public RoleManager(RoleConfig rConfig) {
		this.rConfig = rConfig;
	}
	
	// M�thode qui permet d'avoir le r�le d'un joueur
	public RoleEnum getRole(Player p) {
		YamlConfiguration config = rConfig.getNewConfiguration();
		if(config.getString("Player"+p.getName()).equals("Voleur")) return RoleEnum.VOLEUR;
		else if(config.getString("Player"+p.getName()).equals("Loup-Garou")) return RoleEnum.LOUP_GAROU;
		else if(config.getString("Player"+p.getName()).equals("Simple Villageois")) return RoleEnum.SIMPLE_VILLAGEOIS;
		else if(config.getString("Player"+p.getName()).equals("Chasseur")) return RoleEnum.CHASSEUR;
		else if(config.getString("Player"+p.getName()).equals("Sorci�re")) return RoleEnum.SORCI�RE;
		else if(config.getString("Player"+p.getName()).equals("Cupidon")) return RoleEnum.CUPIDON;
		else if(config.getString("Player"+p.getName()).equals("Petite fille")) return RoleEnum.PETITE_FILLE;
		else if(config.getString("Player"+p.getName()).equals("Voyante")) return RoleEnum.VOYANTE;
		else return null;
	}
	
	// M�thode qui permet de tester si un joueur poss�de un r�le x
	public boolean isRole(Player p, RoleEnum role) {
		return role.equals(this.getRole(p));
	}
	
	// M�thode qui attribut all�atoirement les r�les
	public void randomiser() {
		YamlConfiguration config = rConfig.getNewConfiguration();
		List<String> rRole = new ArrayList<String>();
		// On ajoute tout les r�les activ� et le bon nombre de loups-garous et villageois dans les r�les possibles
		if(config.getBoolean("Role.Sorci�re")) rRole.add("Sorci�re");
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
		// Si le voleur est activ� on prend 2 r�le au hasard qui seront le r�le que pourra choisir le voleur
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
		// On attribut les r�le restant aux joueurs de la partie
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
	
	// M�thodes qui permet d'initialiser la partie
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
		// S'il y a une sorci�re on l'initialise
		if(config.getBoolean("Role.Sorci�re")) {
			Role Sorci�re = new Sorci�re(this.rConfig);
			Sorci�re.setupPlayer();
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
