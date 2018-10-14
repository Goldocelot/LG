package be.goldocelot.lg.role;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import be.goldocelot.lg.utils.ItemStackCreator;

/**
 * 
 * @author Nicolas Gerard (Goldocelot)
 * Class qui permet de gérer l'interface de l'utilisateur en la créant est en l'envoyant au joueur
 */
public class RoleGuiManager {
	
	private RoleConfig rConfig;
	
	public RoleGuiManager(RoleConfig rConfig) {
		this.rConfig = rConfig;
	}
	
	// Méthode qui créé le menu général des rôles
	private Inventory createRoleGui() {
		YamlConfiguration config = this.rConfig.getNewConfiguration();
		// Si le nombre de villageoi est sur des valeurs impossible on le remet a la valeur minimal possible
		if(config.getInt("Role.Simples Villageois") < 0) config.set("Role.Simples Villageois", 0);
		// Si le nombre de loup-garou est sur des valeurs impossible on le remet a la valeur minimal possible
		if(config.getInt("Role.Loups-Garous") < 1) config.set("Role.Loups-Garous", 1);
		// Création du menu général de l'utilisateur
		Inventory roleGui = Bukkit.createInventory(null, 27, "§9 Menu des rôles");
		// On remplit les trous avec des bloc de feuille pour le visuel
		for(int a=0 ; a<26 ; a++) {
			roleGui.setItem(a, new ItemStackCreator("", 1, Material.LEAVES, (byte) 0).create());
		}
		// Si le rôle chasseur est activé on met de l'argile vert sinon de l'argile rouge pour les représenter
		if(config.getBoolean("Role.Chasseur")) {
			roleGui.setItem(4, new ItemStackCreator("Chasseur", 1, Material.STAINED_CLAY, (byte) 5).create());
		}else {
			roleGui.setItem(4, new ItemStackCreator("Chasseur", 1, Material.STAINED_CLAY, (byte) 14).create());
		}
		// On ajoute un nombre x de bloc d'argile rose pour représenter le nombre x de villageois
		roleGui.setItem(10, new ItemStackCreator("Simples Villageois", config.getInt("Role.Simples Villageois"), Material.STAINED_CLAY, (byte) 0).create());
		// Si le rôle voyante est activé on met de l'argile vert sinon de l'argile rouge pour les représenter
		if(config.getBoolean("Role.Voyante")) {
			roleGui.setItem(11, new ItemStackCreator("Voyante", 1, Material.STAINED_CLAY, (byte) 5).create());
		}else {
			roleGui.setItem(11, new ItemStackCreator("Voyante", 1, Material.STAINED_CLAY, (byte) 14).create());
		}
		// Si le rôle sorcière est activé on met de l'argile vert sinon de l'argile rouge pour les représenter
		if(config.getBoolean("Role.Sorcière")) {
			roleGui.setItem(12, new ItemStackCreator("Sorcière", 1, Material.STAINED_CLAY, (byte) 5).create());
		}else {
			roleGui.setItem(12, new ItemStackCreator("Sorcière", 1, Material.STAINED_CLAY, (byte) 14).create());
		}
		// Si le rôle petite fille est activé on met de l'argile vert sinon de l'argile rouge pour les représenter
		if(config.getBoolean("Role.Petite fille")) {
			roleGui.setItem(13, new ItemStackCreator("Petite fille", 1, Material.STAINED_CLAY, (byte) 5).create());
		}else {
			roleGui.setItem(13, new ItemStackCreator("Petite fille", 1, Material.STAINED_CLAY, (byte) 14).create());
		}
		// Si le rôle cupidon est activé on met de l'argile vert sinon de l'argile rouge pour les représenter
		if(config.getBoolean("Role.Cupidon")) {
			roleGui.setItem(14, new ItemStackCreator("Cupidon", 1, Material.STAINED_CLAY, (byte) 5).create());
		}else {
			roleGui.setItem(14, new ItemStackCreator("Cupidon", 1, Material.STAINED_CLAY, (byte) 14).create());
		}
		// Si le rôle voleur est activé on met de l'argile vert sinon de l'argile rouge pour les représenter
		if(config.getBoolean("Role.Voleur")) {
			roleGui.setItem(15, new ItemStackCreator("Voleur", 1, Material.STAINED_CLAY, (byte) 5).create());
		}else {
			roleGui.setItem(15, new ItemStackCreator("Voleur", 1, Material.STAINED_CLAY, (byte) 14).create());
		}
		// On ajoute un nombre x de bloc d'argile brun foncé pour représenter le nombre x de loup-garou
		roleGui.setItem(16, new ItemStackCreator("Loups-Garous", config.getInt("Role.Loups-Garous"), Material.STAINED_CLAY, (byte) 12).create());
		// Si le rôle voleur est activé
		if(config.getBoolean("Role.Voleur")) {
			// On calcul le nombre de rôle activé
			int a = 1;
			if(config.getBoolean("Role.Voyante")) a++;
			if(config.getBoolean("Role.Chasseur")) a++;
			if(config.getBoolean("Role.Sorcière")) a++;
			if(config.getBoolean("Role.Petite fille")) a++;
			if(config.getBoolean("Role.Cupidon")) a++;
			a = a+config.getInt("Role.Loups-Garous");
			a = a+config.getInt("Role.Simples Villageois");
			// Si le nombre de rôle activé est égal au nombre de joueur+2 (car le voleur a le choix entre 2 rôle en début de partie)
			if(a==Bukkit.getOnlinePlayers().size()+2) {
				// On active le bouton valider
				roleGui.setItem(25, new ItemStackCreator("Valider", 1, Material.EMERALD_BLOCK, (byte) 0).create());
			}else {
				// Sinon on indique a l'utilisateur le nombre de rôle manquant
				int b = Bukkit.getOnlinePlayers().size()+2;
				roleGui.setItem(25, new ItemStackCreator("Nombre de rôles requis: "+b, b, Material.STONE, (byte) 0).create());
			}
		// S'il le voleur n'est pas activé
		}else {
			// On calcul le nombre de rôle activé
			// (Mémo: Peut être créé une méthode pour le faire afin d'optimiser le code si ces lignes ce répéte encore plusieur fois
			int a = 0;
			if(config.getBoolean("Role.Voyante")) a++;
			if(config.getBoolean("Role.Sorcière")) a++;
			if(config.getBoolean("Role.Chasseur")) a++;
			if(config.getBoolean("Role.Petite fille")) a++;
			if(config.getBoolean("Role.Cupidon")) a++;
			a = a+config.getInt("Role.Loups-Garous");
			a = a+config.getInt("Role.Simples Villageois");
			// Si le nombre de rôle activé est égal au nombre de joueur
			if(a==Bukkit.getOnlinePlayers().size()) {
				// On active le bouton valider
				roleGui.setItem(25, new ItemStackCreator("Valider", 1, Material.EMERALD_BLOCK, (byte) 0).create());
			}else {
				// Sinon on indique a l'utilisateur le nombre de rôle manquant
				int b = Bukkit.getOnlinePlayers().size();
				roleGui.setItem(25, new ItemStackCreator("Nombre de rôles requis: "+b, b, Material.STONE, (byte) 0).create());
			}
		}
		// On ajoute un bouton annuler
		roleGui.setItem(26, new ItemStackCreator("Annuler", 1, Material.REDSTONE_BLOCK, (byte) 0).create());
		try {
			config.save(rConfig.getFile());
		} catch (IOException io) {
		 	io.printStackTrace();
		}
		return roleGui;	
	}
	
	// Méthode qui créé le menu de configuration du nombre de villageois
	private Inventory createSVGui() {
		YamlConfiguration config = this.rConfig.getNewConfiguration();
		// Création de menu de manipulation du nombre de villageois
		Inventory svGui = Bukkit.createInventory(null, 27, "§9 Menu des simples villageois");
		// On remplit les trous avec des bloc de feuille pour le visuel
		for(int a=0 ; a<26 ; a++) {
			svGui.setItem(a, new ItemStackCreator("", 1, Material.LEAVES, (byte) 0).create());
		}
		// On rajoute des pack de -3/-2/-1 argiles rouges
		svGui.setItem(10, new ItemStackCreator("-3", 3, Material.STAINED_CLAY, (byte) 14).create());
		svGui.setItem(11, new ItemStackCreator("-2", 2, Material.STAINED_CLAY, (byte) 14).create());
		svGui.setItem(12, new ItemStackCreator("-1", 1, Material.STAINED_CLAY, (byte) 14).create());
		// On rajoute x argiles rose dont le nombre représente le nombre de villageois
		svGui.setItem(13, new ItemStackCreator("Nombre de simples villageois", config.getInt("Role.Simples Villageois"), Material.STAINED_CLAY, (byte) 0).create());
		// On rajoute des pack de 3/2/1 argiles vertes
		svGui.setItem(14, new ItemStackCreator("1", 1, Material.STAINED_CLAY, (byte) 5).create());
		svGui.setItem(15, new ItemStackCreator("2", 2, Material.STAINED_CLAY, (byte) 5).create());
		svGui.setItem(16, new ItemStackCreator("3", 3, Material.STAINED_CLAY, (byte) 5).create());
		// Si le nombre de villageois n'est pas négatif
		if(config.getInt("Role.Simples Villageois")>=0) {
			// On ajoute le bouton valider
			svGui.setItem(26, new ItemStackCreator("Valider", 1, Material.EMERALD_BLOCK, (byte) 0).create());
		}else {
			// Sinon on bloque la validation avec un bloc de stone qui indique au joueur les conditions pour pouvoir valider
			svGui.setItem(26, new ItemStackCreator("Le nombre de simples villageois doit être supérieur ou égal à 0", 1, Material.STONE, (byte) 0).create());
		}
		return svGui;
	}
	
	// Méthode qui créé le menu de configuration du nombre de loups-garous
	private Inventory createLGGUI() {
		YamlConfiguration config = this.rConfig.getNewConfiguration();
		// Création de menu de manipulation du nombre de loups-garous
		Inventory lgGui = Bukkit.createInventory(null, 27, "§9 Menu des loups-garous");
		// On remplit les trous avec des bloc de feuille pour le visuel
		for(int a=0 ; a<26 ; a++) {
			lgGui.setItem(a, new ItemStackCreator("", 1, Material.LEAVES, (byte) 0).create());
		}
		// On rajoute des pack de -3/-2/-1 argiles rouges
		lgGui.setItem(10, new ItemStackCreator("-3", 3, Material.STAINED_CLAY, (byte) 14).create());
		lgGui.setItem(11, new ItemStackCreator("-2", 2, Material.STAINED_CLAY, (byte) 14).create());
		lgGui.setItem(12, new ItemStackCreator("-1", 1, Material.STAINED_CLAY, (byte) 14).create());
		// On rajoute x argiles brun foncé dont le nombre représente le nombre de loups-garous
		lgGui.setItem(13, new ItemStackCreator("Nombre de loups-garous", config.getInt("Role.Loups-Garous"), Material.STAINED_CLAY, (byte) 12).create());
		// On rajoute des pack de 3/2/1 argiles vertes
		lgGui.setItem(14, new ItemStackCreator("1", 1, Material.STAINED_CLAY, (byte) 5).create());
		lgGui.setItem(15, new ItemStackCreator("2", 2, Material.STAINED_CLAY, (byte) 5).create());
		lgGui.setItem(16, new ItemStackCreator("3", 3, Material.STAINED_CLAY, (byte) 5).create());
		// Si le nombre de loups-garous est minimum de 1
		if(config.getInt("Role.Loups-Garous")>=1) {
			// On ajoute le bouton valider
			lgGui.setItem(26, new ItemStackCreator("Valider", 1, Material.EMERALD_BLOCK, (byte) 0).create());
		}else {
			// Sinon on bloque la validation avec un bloc de stone qui indique au joueur les conditions pour pouvoir valider
			lgGui.setItem(26, new ItemStackCreator("Le nombre de loups-garous doit être supérieur ou égal à 1", 1, Material.STONE, (byte) 0).create());
		}
		return lgGui;
	}
	
	// Méthode qui envoie le menu général des rôles
	public void sendRoleGui(Player p) {
		p.openInventory(this.createRoleGui());
	}
	
	// Méthode qui envoie le menu de configuration du nombre de villageois
	public void sendSVGui(Player p) {
		p.openInventory(this.createSVGui());
	}
	
	// Méthode qui envoie le menu de configuration du nombre de Loups-Garous
	public void sendLGGUI(Player p) {
		p.openInventory(this.createLGGUI());
	}
}