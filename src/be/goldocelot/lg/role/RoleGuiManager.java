package be.goldocelot.lg.role;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import be.goldocelot.lg.utils.ItemStackCreator;

public class RoleGuiManager {
	
	private RoleConfig rConfig;
	
	public RoleGuiManager(RoleConfig rConfig) {
		this.rConfig = rConfig;
	}
	
	private Inventory createRoleGui() {
		YamlConfiguration config = this.rConfig.getNewConfiguration();
		if(config.getInt("Role.Simples Villageois") < 0) config.set("Role.Simples Villageois", 0);
		if(config.getInt("Role.Loups-Garous") < 1) config.set("Role.Loups-Garous", 1);
		Inventory roleGui = Bukkit.createInventory(null, 27, "�9 Menu des r�les");
		for(int a=0 ; a<26 ; a++) {
			roleGui.setItem(a, new ItemStackCreator("", 1, Material.LEAVES, (byte) 0).create());
		}
		if(config.getBoolean("Role.Chasseur")) {
			roleGui.setItem(4, new ItemStackCreator("Chasseur", 1, Material.STAINED_CLAY, (byte) 5).create());
		}else {
			roleGui.setItem(4, new ItemStackCreator("Chasseur", 1, Material.STAINED_CLAY, (byte) 14).create());
		}
		roleGui.setItem(10, new ItemStackCreator("Simples Villageois", config.getInt("Role.Simples Villageois"), Material.STAINED_CLAY, (byte) 0).create());
		if(config.getBoolean("Role.Voyante")) {
			roleGui.setItem(11, new ItemStackCreator("Voyante", 1, Material.STAINED_CLAY, (byte) 5).create());
		}else {
			roleGui.setItem(11, new ItemStackCreator("Voyante", 1, Material.STAINED_CLAY, (byte) 14).create());
		}
		if(config.getBoolean("Role.Sorci�re")) {
			roleGui.setItem(12, new ItemStackCreator("Sorci�re", 1, Material.STAINED_CLAY, (byte) 5).create());
		}else {
			roleGui.setItem(12, new ItemStackCreator("Sorci�re", 1, Material.STAINED_CLAY, (byte) 14).create());
		}
		if(config.getBoolean("Role.Petite fille")) {
			roleGui.setItem(13, new ItemStackCreator("Petite fille", 1, Material.STAINED_CLAY, (byte) 5).create());
		}else {
			roleGui.setItem(13, new ItemStackCreator("Petite fille", 1, Material.STAINED_CLAY, (byte) 14).create());
		}
		if(config.getBoolean("Role.Cupidon")) {
			roleGui.setItem(14, new ItemStackCreator("Cupidon", 1, Material.STAINED_CLAY, (byte) 5).create());
		}else {
			roleGui.setItem(14, new ItemStackCreator("Cupidon", 1, Material.STAINED_CLAY, (byte) 14).create());
		}
		if(config.getBoolean("Role.Voleur")) {
			roleGui.setItem(15, new ItemStackCreator("Voleur", 1, Material.STAINED_CLAY, (byte) 5).create());
		}else {
			roleGui.setItem(15, new ItemStackCreator("Voleur", 1, Material.STAINED_CLAY, (byte) 14).create());
		}
		roleGui.setItem(16, new ItemStackCreator("Loups-Garous", config.getInt("Role.Loups-Garous"), Material.STAINED_CLAY, (byte) 12).create());
		if(config.getBoolean("Role.Voleur")) {
			int a = 1;
			if(config.getBoolean("Role.Voyante")) a++;
			if(config.getBoolean("Role.Chasseur")) a++;
			if(config.getBoolean("Role.Sorci�re")) a++;
			if(config.getBoolean("Role.Petite fille")) a++;
			if(config.getBoolean("Role.Cupidon")) a++;
			a = a+config.getInt("Role.Loups-Garous");
			a = a+config.getInt("Role.Simples Villageois");
			if(a==Bukkit.getOnlinePlayers().size()+2) {
				roleGui.setItem(25, new ItemStackCreator("Valider", 1, Material.EMERALD_BLOCK, (byte) 0).create());
			}else {
				int b = Bukkit.getOnlinePlayers().size()+2;
				roleGui.setItem(25, new ItemStackCreator("Nombre de r�les requis: "+b, b, Material.STONE, (byte) 0).create());
			}
		}else {
			int a = 0;
			if(config.getBoolean("Role.Voyante")) a++;
			if(config.getBoolean("Role.Sorci�re")) a++;
			if(config.getBoolean("Role.Chasseur")) a++;
			if(config.getBoolean("Role.Petite fille")) a++;
			if(config.getBoolean("Role.Cupidon")) a++;
			a = a+config.getInt("Role.Loups-Garous");
			a = a+config.getInt("Role.Simples Villageois");
			if(a==Bukkit.getOnlinePlayers().size()) {
				roleGui.setItem(25, new ItemStackCreator("Valider", 1, Material.EMERALD_BLOCK, (byte) 0).create());
			}else {
				int b = Bukkit.getOnlinePlayers().size();
				roleGui.setItem(25, new ItemStackCreator("Nombre de r�les requis: "+b, b, Material.STONE, (byte) 0).create());
			}
		}
		roleGui.setItem(26, new ItemStackCreator("Annuler", 1, Material.REDSTONE_BLOCK, (byte) 0).create());
		try {
			config.save(rConfig.getFile());
		} catch (IOException io) {
		 	io.printStackTrace();
		}
		return roleGui;	
	}
	
	private Inventory createSVGui() {
		YamlConfiguration config = this.rConfig.getNewConfiguration();
		Inventory svGui = Bukkit.createInventory(null, 27, "�9 Menu des simples villageois");
		for(int a=0 ; a<26 ; a++) {
			svGui.setItem(a, new ItemStackCreator("", 1, Material.LEAVES, (byte) 0).create());
		}
		svGui.setItem(10, new ItemStackCreator("-3", 3, Material.STAINED_CLAY, (byte) 14).create());
		svGui.setItem(11, new ItemStackCreator("-2", 2, Material.STAINED_CLAY, (byte) 14).create());
		svGui.setItem(12, new ItemStackCreator("-1", 1, Material.STAINED_CLAY, (byte) 14).create());
		svGui.setItem(13, new ItemStackCreator("Nombre de simples villageois", config.getInt("Role.Simples Villageois"), Material.STAINED_CLAY, (byte) 0).create());
		svGui.setItem(14, new ItemStackCreator("1", 1, Material.STAINED_CLAY, (byte) 5).create());
		svGui.setItem(15, new ItemStackCreator("2", 2, Material.STAINED_CLAY, (byte) 5).create());
		svGui.setItem(16, new ItemStackCreator("3", 3, Material.STAINED_CLAY, (byte) 5).create());
		if(config.getInt("Role.Simples Villageois")>=0) {
			svGui.setItem(26, new ItemStackCreator("Valider", 1, Material.EMERALD_BLOCK, (byte) 0).create());
		}else {
			svGui.setItem(26, new ItemStackCreator("Le nombre de simples villageois doit �tre sup�rieur ou �gal � 0", 1, Material.STONE, (byte) 0).create());
		}
		return svGui;
	}
	
	private Inventory createLGGUI() {
		YamlConfiguration config = this.rConfig.getNewConfiguration();
		Inventory lgGui = Bukkit.createInventory(null, 27, "�9 Menu des loups-garous");
		for(int a=0 ; a<26 ; a++) {
			lgGui.setItem(a, new ItemStackCreator("", 1, Material.LEAVES, (byte) 0).create());
		}
		lgGui.setItem(10, new ItemStackCreator("-3", 3, Material.STAINED_CLAY, (byte) 14).create());
		lgGui.setItem(11, new ItemStackCreator("-2", 2, Material.STAINED_CLAY, (byte) 14).create());
		lgGui.setItem(12, new ItemStackCreator("-1", 1, Material.STAINED_CLAY, (byte) 14).create());
		lgGui.setItem(13, new ItemStackCreator("Nombre de loups-garous", config.getInt("Role.Loups-Garous"), Material.STAINED_CLAY, (byte) 12).create());
		lgGui.setItem(14, new ItemStackCreator("1", 1, Material.STAINED_CLAY, (byte) 5).create());
		lgGui.setItem(15, new ItemStackCreator("2", 2, Material.STAINED_CLAY, (byte) 5).create());
		lgGui.setItem(16, new ItemStackCreator("3", 3, Material.STAINED_CLAY, (byte) 5).create());
		if(config.getInt("Role.Loups-Garous")>=1) {
			lgGui.setItem(26, new ItemStackCreator("Valider", 1, Material.EMERALD_BLOCK, (byte) 0).create());
		}else {
			lgGui.setItem(26, new ItemStackCreator("Le nombre de loups-garous doit �tre sup�rieur ou �gal � 1", 1, Material.STONE, (byte) 0).create());
		}
		return lgGui;
	}
	
	public void sendRoleGui(Player p) {
		p.openInventory(this.createRoleGui());
	}
	
	public void sendSVGui(Player p) {
		p.openInventory(this.createSVGui());
	}
	
	public void sendLGGUI(Player p) {
		p.openInventory(this.createLGGUI());
	}
}