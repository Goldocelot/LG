package be.goldocelot.lg.utils;


import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ArmorContents {
	
	private ItemStack item;
	
	public ArmorContents(ItemStack item) {
		this.item = item;
	}
	
	public void equip(Player p) {
		switch(item.getType()) {
		case CHAINMAIL_BOOTS:{
			p.getEquipment().setBoots(this.item);
		}
			break;
		case CHAINMAIL_CHESTPLATE:{
			p.getEquipment().setChestplate(this.item);
		}
			break;
		case CHAINMAIL_HELMET:{
			p.getEquipment().setHelmet(this.item);
		}
			break;
		case CHAINMAIL_LEGGINGS:{
			p.getEquipment().setLeggings(this.item);
		}
			break;
		case DIAMOND_BOOTS:{
			p.getEquipment().setBoots(this.item);
		}
			break;
		case DIAMOND_CHESTPLATE:{
			p.getEquipment().setChestplate(this.item);
		}
			break;
		case DIAMOND_HELMET:{
			p.getEquipment().setHelmet(this.item);
		}
			break;
		case DIAMOND_LEGGINGS:{
			p.getEquipment().setLeggings(this.item);
		}
			break;
		case GOLD_BOOTS:{
			p.getEquipment().setBoots(this.item);
		}
			break;
		case GOLD_CHESTPLATE:{
			p.getEquipment().setChestplate(this.item);
		}
			break;
		case GOLD_HELMET:{
			p.getEquipment().setHelmet(this.item);
		}
			break;
		case GOLD_LEGGINGS:{
			p.getEquipment().setLeggings(this.item);
		}
			break;
		case IRON_BOOTS:{
			p.getEquipment().setBoots(this.item);
		}
			break;
		case IRON_CHESTPLATE:
			break;
		case IRON_HELMET:{
			p.getEquipment().setHelmet(this.item);
		}
			break;
		case IRON_LEGGINGS:{
			p.getEquipment().setLeggings(this.item);
		}
			break;
		case LEATHER_BOOTS:{
			p.getEquipment().setBoots(this.item);
		}
			break;
		case LEATHER_CHESTPLATE:{
			p.getEquipment().setChestplate(this.item);
		}
			break;
		case LEATHER_HELMET:{
			p.getEquipment().setHelmet(this.item);
		}
			break;
		case LEATHER_LEGGINGS:{
			p.getEquipment().setLeggings(this.item);
		}
			break;
		default:{
			p.getInventory().addItem(this.item);
		}
			break;
		
		}
	}
}