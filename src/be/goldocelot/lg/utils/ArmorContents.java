package be.goldocelot.lg.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ArmorContents {
	
	private ItemStack item;
	
	public ArmorContents(ItemStack item) {
		this.item = item;
	}
	
	public boolean isHelmet() {
		return this.item.getType().equals(Material.LEATHER_HELMET);
	}
	
	public boolean isChestplate() {
		return this.item.getType().equals(Material.LEATHER_CHESTPLATE);
	}
	
	public boolean isLeggings() {
		return this.item.getType().equals(Material.LEATHER_LEGGINGS);
	}
	
	public boolean isBoots() {
		return this.item.getType().equals(Material.LEATHER_BOOTS);
	}
	
	public boolean isItem() {
		return !this.item.getType().equals(Material.LEATHER_HELMET) && !this.item.getType().equals(Material.LEATHER_CHESTPLATE)
				&& !this.item.getType().equals(Material.LEATHER_LEGGINGS) && !this.item.getType().equals(Material.LEATHER_BOOTS);
	}
}