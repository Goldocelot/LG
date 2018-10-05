package be.goldocelot.lg.role;

import java.io.IOException;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import be.goldocelot.lg.utils.ItemStackCreator;

/**
 * 
 * @author Nicolas Gerard (Goldocelot)
 * Class qui contient les �venement li� a l'int�raction avec le menu gui de l'utilisateur
 */
public class RoleGuiEvent implements Listener {

	private RoleConfig rConfig;
	private RoleGuiManager rGManager;
	private RoleManager rManager;
	
	public RoleGuiEvent(RoleConfig rConfig, RoleGuiManager rGManager, RoleManager rManager) {
		this.rConfig = rConfig;
		this.rGManager = rGManager;
		this.rManager = rManager;
	}
	
	// Lors d'un clique dans le menu
	@EventHandler
	private void onClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		Inventory i = e.getInventory();
		ItemStack item = e.getCurrentItem();
		YamlConfiguration config = rConfig.getNewConfiguration();
		
		// On v�rifi� s'il s'agit du menu des r�mes
		if(i.getName().equals("�9 Menu des r�les")) {
			// Si l'item cliqu� est des feuillages on ne fait rien car c'est juste d�coratif
			if(item.equals(new ItemStackCreator("", 1, Material.LEAVES, (byte) 0).create())) {
				e.setCancelled(true);
				return;
			// Si l'item cliqu� est de l'argile vert on active le r�le, rouge on le d�sactive
			}else if(item.equals(new ItemStackCreator("Voyante", 1, Material.STAINED_CLAY, (byte) 5).create())) {
				config.set("Role.Voyante", false);
			}else if(item.equals(new ItemStackCreator("Voyante", 1, Material.STAINED_CLAY, (byte) 14).create())) {
				config.set("Role.Voyante", true);
			}else if(item.equals(new ItemStackCreator("Sorci�re", 1, Material.STAINED_CLAY, (byte) 5).create())) {
				config.set("Role.Sorci�re", false);
			}else if(item.equals(new ItemStackCreator("Sorci�re", 1, Material.STAINED_CLAY, (byte) 14).create())) {
				config.set("Role.Sorci�re", true);
			}else if(item.equals(new ItemStackCreator("Petite fille", 1, Material.STAINED_CLAY, (byte) 5).create())) {
				config.set("Role.Petite fille", false);
			}else if(item.equals(new ItemStackCreator("Petite fille", 1, Material.STAINED_CLAY, (byte) 14).create())) {
				config.set("Role.Petite fille", true);
			}else if(item.equals(new ItemStackCreator("Cupidon", 1, Material.STAINED_CLAY, (byte) 5).create())) {
				config.set("Role.Cupidon", false);
			}else if(item.equals(new ItemStackCreator("Cupidon", 1, Material.STAINED_CLAY, (byte) 14).create())) {
				config.set("Role.Cupidon", true);
			}else if(item.equals(new ItemStackCreator("Voleur", 1, Material.STAINED_CLAY, (byte) 5).create())) {
				config.set("Role.Voleur", false);
			}else if(item.equals(new ItemStackCreator("Voleur", 1, Material.STAINED_CLAY, (byte) 14).create())) {
				config.set("Role.Voleur", true);
			}else if(item.equals(new ItemStackCreator("Chasseur", 1, Material.STAINED_CLAY, (byte) 5).create())) {
				config.set("Role.Chasseur", false);
			}else if(item.equals(new ItemStackCreator("Chasseur", 1, Material.STAINED_CLAY, (byte) 14).create())) {
				config.set("Role.Chasseur", true);
			// Si l'item cliqu� est de l'argile rose on ouvre le menu qui permet de rajouter/supprimer des villageois
			}else if(item.equals(new ItemStackCreator("Simples Villageois", config.getInt("Role.Simples Villageois"), Material.STAINED_CLAY, (byte) 0).create())) {
				e.setCancelled(true);
				p.closeInventory();
				rGManager.sendSVGui(p);
				return;
			// Si l'item cliqu� est de l'argile brun on ouvre le menu qui permet de rajouter/supprimer des loups-garous
			}else if(item.equals(new ItemStackCreator("Loups-Garous", config.getInt("Role.Loups-Garous"), Material.STAINED_CLAY, (byte) 12).create())) {
				e.setCancelled(true);
				p.closeInventory();
				rGManager.sendLGGUI(p);
				return;
			// Si l'item cliqu� est un bloc d'�meraude on valide la configuration et lance la partie
			}else if(item.equals(new ItemStackCreator("Valider", 1, Material.EMERALD_BLOCK, (byte) 0).create())){
				e.setCancelled(true);
				p.closeInventory();
				rManager.randomiser();
				rManager.initialiser();
				return;
			// Si l'item cliqu� est une stone on fait rien car �a sert a bloqu� le lancement de la partie avec une composition non valide
			}else if(item.getType().equals(Material.STONE)) {
				e.setCancelled(true);
				return;
			// Si l'item cliqu� est de la redstone on ferme le menu sans lancer la partie mais en sauvegardant la configuration s�l�ctionn�e
			}else if(item.equals(new ItemStackCreator("Annuler", 1, Material.REDSTONE_BLOCK, (byte) 0).create())) {
				e.setCancelled(true);
				p.closeInventory();
				return;
			}
			e.setCancelled(true);
			p.closeInventory();
			try {
				config.save(rConfig.getFile());
			} catch (IOException io) {
			 	io.printStackTrace();
			}
			rGManager.sendRoleGui(p);
			// Si on est dans le menu de configuration du nombre de villageois
		}else if(i.getName().equals("�9 Menu des simples villageois")) {
			// Si l'item cliqu� est des feuillages on ne fait rien car c'est juste d�coratif
			if(item.equals(new ItemStackCreator("", 1, Material.LEAVES, (byte) 0).create())) {
				e.setCancelled(true);
				return;
			// Si l'item cliqu� est x argiles rouges on retire x villageois
			}else if(item.equals(new ItemStackCreator("-3", 3, Material.STAINED_CLAY, (byte) 14).create())) {
				config.set("Role.Simples Villageois", config.getInt("Role.Simples Villageois")-3);
			}else if(item.equals(new ItemStackCreator("-2", 2, Material.STAINED_CLAY, (byte) 14).create())) {
				config.set("Role.Simples Villageois", config.getInt("Role.Simples Villageois")-2);
			}else if(item.equals(new ItemStackCreator("-1", 1, Material.STAINED_CLAY, (byte) 14).create())) {
				config.set("Role.Simples Villageois", config.getInt("Role.Simples Villageois")-1);
			// Si l'item cliqu� est x argiles vertes on ajoute x villageois
			}else if(item.equals(new ItemStackCreator("1", 1, Material.STAINED_CLAY, (byte) 5).create())) {
				config.set("Role.Simples Villageois", config.getInt("Role.Simples Villageois")+1);
			}else if(item.equals(new ItemStackCreator("2", 2, Material.STAINED_CLAY, (byte) 5).create())) {
				config.set("Role.Simples Villageois", config.getInt("Role.Simples Villageois")+2);
			}else if(item.equals(new ItemStackCreator("3", 3, Material.STAINED_CLAY, (byte) 5).create())) {
				config.set("Role.Simples Villageois", config.getInt("Role.Simples Villageois")+3);
			// Si l'item cliqu� est de l'argile rose on ne fait rien car �a affiche uniquement le nombre actuel de villageois
			}else if(item.equals(new ItemStackCreator("Nombre de simples villageois", config.getInt("Role.Simples Villageois"), Material.STAINED_CLAY, (byte) 0).create())) {
				e.setCancelled(true);
				return;
			// Si l'item cliqu� est de la stone on ne fait rien car �a bloque juste la validation de configuration non valide
			}else if(item.equals(new ItemStackCreator("Le nombre de simples villageois doit �tre sup�rieur ou �gal � 0", 1, Material.STONE, (byte) 0).create())) {
				e.setCancelled(true);
				return;
			// Si l'item cliqu� est un bloc d'�meraude on valide le nombre de villageois et on retourne de le menu g�n�ral de l'utilisateur
			}else if(item.equals(new ItemStackCreator("Valider", 1, Material.EMERALD_BLOCK, (byte) 0).create())) {
				e.setCancelled(true);
				p.closeInventory();
				rGManager.sendRoleGui(p);
				return;
			}
			e.setCancelled(true);
			p.closeInventory();
			try {
				config.save(rConfig.getFile());
			} catch (IOException io) {
			 	io.printStackTrace();
			}
			rGManager.sendSVGui(p);
		// Si on est dans le menu de configuration du nombre de Loups-garous
		}else if(i.getName().equals("�9 Menu des loups-garous")) {
			// Si l'item cliqu� est des feuillages on ne fait rien car c'est juste d�coratif
			if(item.equals(new ItemStackCreator("", 1, Material.LEAVES, (byte) 0).create())) {
				e.setCancelled(true);
				return;
			// Si l'item cliqu� est x argiles rouges on retire x loups-garous
			}else if(item.equals(new ItemStackCreator("-3", 3, Material.STAINED_CLAY, (byte) 14).create())) {
				config.set("Role.Loups-Garous", config.getInt("Role.Loups-Garous")-3);
			}else if(item.equals(new ItemStackCreator("-2", 2, Material.STAINED_CLAY, (byte) 14).create())) {
				config.set("Role.Loups-Garous", config.getInt("Role.Loups-Garous")-2);
			}else if(item.equals(new ItemStackCreator("-1", 1, Material.STAINED_CLAY, (byte) 14).create())) {
				config.set("Role.Loups-Garous", config.getInt("Role.Loups-Garous")-1);
			// Si l'item cliqu� est x argiles vertes on ajoute x loups-garous
			}else if(item.equals(new ItemStackCreator("1", 1, Material.STAINED_CLAY, (byte) 5).create())) {
				config.set("Role.Loups-Garous", config.getInt("Role.Loups-Garous")+1);
			}else if(item.equals(new ItemStackCreator("2", 2, Material.STAINED_CLAY, (byte) 5).create())) {
				config.set("Role.Loups-Garous", config.getInt("Role.Loups-Garous")+2);
			}else if(item.equals(new ItemStackCreator("3", 3, Material.STAINED_CLAY, (byte) 5).create())) {
				config.set("Role.Loups-Garous", config.getInt("Role.Loups-Garous")+3);
			// Si l'item cliqu� est de l'argile brun fonc� on ne fait rien car �a affiche uniquement le nombre actuel de loup-garou
			}else if(item.equals( new ItemStackCreator("Nombre de loups-garous", config.getInt("Role.Loups-Garous"), Material.STAINED_CLAY, (byte) 12).create())) {
				e.setCancelled(true);
				return;
			// Si l'item cliqu� est de la stone on ne fait rien car �a bloque juste la validation de configuration non valide
			}else if(item.equals(new ItemStackCreator("Le nombre de loups-garous doit �tre sup�rieur ou �gal � 1", 1, Material.STONE, (byte) 0).create())) {
				e.setCancelled(true);
				return;
			// Si l'item cliqu� est un bloc d'�meraude on valide le nombre de loups-garous et on retourne de le menu g�n�ral de l'utilisateur
			}else if(item.equals(new ItemStackCreator("Valider", 1, Material.EMERALD_BLOCK, (byte) 0).create())) {
				e.setCancelled(true);
				p.closeInventory();
				rGManager.sendRoleGui(p);
				return;
			}
			e.setCancelled(true);
			p.closeInventory();
			try {
				config.save(rConfig.getFile());
			} catch (IOException io) {
			 	io.printStackTrace();
			}
			rGManager.sendLGGUI(p);
		}
	}
	
	// Lors de l'ouverture d'un menu
	@EventHandler
	private void onOpen(InventoryOpenEvent e) {
		Inventory i = e.getInventory();
		YamlConfiguration config = rConfig.getNewConfiguration();
		// Si c'est le menu g�n�ral de l'utilisateur
		if(i.getName().equals("�9 Menu des r�les")) {
			// On d�sactive l'option de role automatique (non implant� 05/10/2018)
			config.set("Role.Automatique", false);
			try {
				config.save(rConfig.getFile());
			} catch (IOException io) {
			 	io.printStackTrace();
			}
		}
	}
	
	// Lors de la fermeture d'un menu
	@EventHandler
	private void onClose(InventoryCloseEvent e) {
		Inventory i = e.getInventory();
		YamlConfiguration config = rConfig.getNewConfiguration();
		// Si c'est le menu g�n�ral de l'utilisateur
		if(i.getName().equals("�9 Menu des r�les")) {
			// On active l'option de role automatique (non implant� 05/10/2018)
			config.set("Role.Automatique", true);
			try {
				config.save(rConfig.getFile());
			} catch (IOException io) {
			 	io.printStackTrace();
			}
		}
	}
}