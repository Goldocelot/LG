package be.goldocelot.lg.role;

import java.util.List;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import be.goldocelot.lg.utils.ArmorContents;

/**
 * 
 * @author Nicolas Gerard (Goldocelot)
 *  Class abstraite qui défini la structure des class relié à chaque rôle
 */
public abstract class Role{
	
	public abstract double MaxHealth();
	public abstract List<Player> players();
	public abstract List<ItemStack> equipement();
	public abstract List<PotionEffect> effect();
	public abstract List<String> rules();
	private RoleConfig rConfig;
	
	public Role(RoleConfig rConfig) {
		this.rConfig = rConfig;
	}
	
	// Méthode qui permet d'initialiser les inventaires, ... des joueurs d'un role
	public void setupPlayer() {
		for(Player p : this.players()) {
			p.getInventory().clear();
			p.setFoodLevel(20);
			p.setMaxHealth(MaxHealth());		
			for(ItemStack item : equipement()){
				new ArmorContents(item).equip(p);
			}
			for(PotionEffect wrongPot : p.getActivePotionEffects()) {
				p.removePotionEffect(wrongPot.getType());
			}
			for(PotionEffect pot : effect()) {
				p.addPotionEffect(pot);
			}
			p.playSound(p.getLocation(), Sound.ORB_PICKUP, 30, 30);
			for(String rule : rules()) {
				p.sendMessage(rule);
			}
		}
	}
	
	public void setupPlayer(Player p) {
		p.getInventory().clear();
		p.setFoodLevel(20);
		p.setMaxHealth(MaxHealth());		
		for(ItemStack item : equipement()){
			new ArmorContents(item).equip(p);
		}
		for(PotionEffect pot : effect()) {
			p.addPotionEffect(pot);
		}
		p.playSound(p.getLocation(), Sound.ORB_PICKUP, 30, 30);
		for(String rule : rules()) {
			p.sendMessage(rule);
		}
	}
	
	public RoleConfig getrConfig() {
		return rConfig;
	}

}