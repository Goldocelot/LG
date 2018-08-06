package be.goldocelot.lg.role;

import java.io.IOException;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import be.goldocelot.lg.utils.ItemStackCreator;

public class RoleGuiEvent implements Listener {

	private RoleConfig rConfig;
	private RoleManager rManager;
	
	public RoleGuiEvent(RoleConfig rConfig, RoleManager rManager) {
		this.rConfig = rConfig;
		this.rManager = rManager;
	}
	
	@EventHandler
	private void onClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		Inventory i = e.getInventory();
		ItemStack item = e.getCurrentItem();
		YamlConfiguration config = rConfig.getNewConfiguration();
		
		if(i.getName().equals("§9 Menu des rôles")) {
			if(item.equals(new ItemStackCreator("", 1, Material.LEAVES, (byte) 0).create())) {
				e.setCancelled(true);
				return;
			}else if(item.equals(new ItemStackCreator("Voyante", 1, Material.STAINED_CLAY, (byte) 5).create())) {
				config.set("Role.Voyante", false);
			}else if(item.equals(new ItemStackCreator("Voyante", 1, Material.STAINED_CLAY, (byte) 14).create())) {
				config.set("Role.Voyante", true);
			}else if(item.equals(new ItemStackCreator("Sorcière", 1, Material.STAINED_CLAY, (byte) 5).create())) {
				config.set("Role.Sorcière", false);
			}else if(item.equals(new ItemStackCreator("Sorcière", 1, Material.STAINED_CLAY, (byte) 14).create())) {
				config.set("Role.Sorcière", true);
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
			}else if(item.equals(new ItemStackCreator("Simples Villageois", config.getInt("Role.Simples Villageois"), Material.STAINED_CLAY, (byte) 0).create())) {
				e.setCancelled(true);
				p.closeInventory();
				rManager.sendSVGui(p);
				return;
			}else if(item.equals(new ItemStackCreator("Loups-Garous", config.getInt("Role.Loups-Garous"), Material.STAINED_CLAY, (byte) 12).create())) {
				e.setCancelled(true);
				p.closeInventory();
				rManager.sendLGGUI(p);
				return;
			}else if(item.equals(new ItemStackCreator("Valider", 1, Material.EMERALD_BLOCK, (byte) 0).create())){
				e.setCancelled(true);
				p.closeInventory();
				//start
				return;
			}else if(item.getType().equals(Material.STONE)) {
				e.setCancelled(true);
				return;
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
			rManager.sendRoleGui(p);
		}else if(i.getName().equals("§9 Menu des simples villageois")) {
			if(item.equals(new ItemStackCreator("", 1, Material.LEAVES, (byte) 0).create())) {
				e.setCancelled(true);
				return;
			}else if(item.equals(new ItemStackCreator("-3", 3, Material.STAINED_CLAY, (byte) 14).create())) {
				config.set("Role.Simples Villageois", config.getInt("Role.Simples Villageois")-3);
			}else if(item.equals(new ItemStackCreator("-2", 2, Material.STAINED_CLAY, (byte) 14).create())) {
				config.set("Role.Simples Villageois", config.getInt("Role.Simples Villageois")-2);
			}else if(item.equals(new ItemStackCreator("-1", 1, Material.STAINED_CLAY, (byte) 14).create())) {
				config.set("Role.Simples Villageois", config.getInt("Role.Simples Villageois")-1);
			}else if(item.equals(new ItemStackCreator("1", 1, Material.STAINED_CLAY, (byte) 5).create())) {
				config.set("Role.Simples Villageois", config.getInt("Role.Simples Villageois")+1);
			}else if(item.equals(new ItemStackCreator("2", 2, Material.STAINED_CLAY, (byte) 5).create())) {
				config.set("Role.Simples Villageois", config.getInt("Role.Simples Villageois")+2);
			}else if(item.equals(new ItemStackCreator("3", 3, Material.STAINED_CLAY, (byte) 5).create())) {
				config.set("Role.Simples Villageois", config.getInt("Role.Simples Villageois")+3);
			}else if(item.equals(new ItemStackCreator("Nombre de simples villageois", config.getInt("Role.Simples Villageois"), Material.STAINED_CLAY, (byte) 0).create())) {
				e.setCancelled(true);
				return;
			}else if(item.equals(new ItemStackCreator("Le nombre de simples villageois doit être supérieur ou égal à 0", 1, Material.STONE, (byte) 0).create())) {
				e.setCancelled(true);
				return;
			}else if(item.equals(new ItemStackCreator("Valider", 1, Material.EMERALD_BLOCK, (byte) 0).create())) {
				e.setCancelled(true);
				p.closeInventory();
				rManager.sendRoleGui(p);
				return;
			}
			e.setCancelled(true);
			p.closeInventory();
			try {
				config.save(rConfig.getFile());
			} catch (IOException io) {
			 	io.printStackTrace();
			}
			rManager.sendSVGui(p);
		}else if(i.getName().equals("§9 Menu des loups-garous")) {
			if(item.equals(new ItemStackCreator("", 1, Material.LEAVES, (byte) 0).create())) {
				e.setCancelled(true);
				return;
			}else if(item.equals(new ItemStackCreator("-3", 3, Material.STAINED_CLAY, (byte) 14).create())) {
				config.set("Role.Loups-Garous", config.getInt("Role.Loups-Garous")-3);
			}else if(item.equals(new ItemStackCreator("-2", 2, Material.STAINED_CLAY, (byte) 14).create())) {
				config.set("Role.Loups-Garous", config.getInt("Role.Loups-Garous")-2);
			}else if(item.equals(new ItemStackCreator("-1", 1, Material.STAINED_CLAY, (byte) 14).create())) {
				config.set("Role.Loups-Garous", config.getInt("Role.Loups-Garous")-1);
			}else if(item.equals(new ItemStackCreator("1", 1, Material.STAINED_CLAY, (byte) 5).create())) {
				config.set("Role.Loups-Garous", config.getInt("Role.Loups-Garous")+1);
			}else if(item.equals(new ItemStackCreator("2", 2, Material.STAINED_CLAY, (byte) 5).create())) {
				config.set("Role.Loups-Garous", config.getInt("Role.Loups-Garous")+2);
			}else if(item.equals(new ItemStackCreator("3", 3, Material.STAINED_CLAY, (byte) 5).create())) {
				config.set("Role.Loups-Garous", config.getInt("Role.Loups-Garous")+3);
			}else if(item.equals( new ItemStackCreator("Nombre de loups-garous", config.getInt("Role.Loups-Garous"), Material.STAINED_CLAY, (byte) 12).create())) {
				e.setCancelled(true);
				return;
			}else if(item.equals(new ItemStackCreator("Le nombre de loups-garous doit être supérieur ou égal à 1", 1, Material.STONE, (byte) 0).create())) {
				e.setCancelled(true);
				return;
			}else if(item.equals(new ItemStackCreator("Valider", 1, Material.EMERALD_BLOCK, (byte) 0).create())) {
				e.setCancelled(true);
				p.closeInventory();
				rManager.sendRoleGui(p);
				return;
			}
			e.setCancelled(true);
			p.closeInventory();
			try {
				config.save(rConfig.getFile());
			} catch (IOException io) {
			 	io.printStackTrace();
			}
			rManager.sendLGGUI(p);
		}
	}
}