package be.goldocelot.lg.role.playable;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import be.goldocelot.lg.utils.ArmorContents;

public abstract class Role{
	
	public abstract double MaxHealth();
	public abstract List<Player> players();
	public abstract List<ItemStack> equipement();
	public abstract List<PotionEffect> effect();
	
	public void setupPlayer() {
		for(Player p : this.players()) {
			p.setMaxHealth(MaxHealth());
			for(ItemStack item : equipement()){
				new ArmorContents(item).equip(p);
			}
			for(PotionEffect pot : effect()) {
				p.addPotionEffect(pot);
			}
		}
	}
}