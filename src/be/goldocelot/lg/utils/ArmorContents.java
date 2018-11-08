package be.goldocelot.lg.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * 
 * @author Nicolas Gerard (Goldocelot), Glowstoner
 * Class utilitaire qui permet d'équiper plus facilement un item à un joueur
 */
public class ArmorContents {
	
	private ItemStack item;
	
	public ArmorContents(ItemStack item) {
		this.item = item;
	}
	
	/* Méthode qui vérifie la nature d'un item (casque, plastron, jambière, botte ou item neutre) 
	et qui le place dans le bon slot du joueur en fonction de celle-ci */
	public void equip(Player p) {
		String mn = this.item.getType().name();
		
		if(mn.endsWith("HELMET")) {
			p.getEquipment().setHelmet(this.item);
		}else if(mn.endsWith("CHESTPLATE")) {
			p.getEquipment().setChestplate(this.item);
		}else if(mn.endsWith("LEGGINGS")) {
			p.getEquipment().setLeggings(this.item);
		}else if(mn.endsWith("BOOTS")) {
			p.getEquipment().setLeggings(this.item);
		}else {
			p.getInventory().addItem(this.item);
		}
	}
}
