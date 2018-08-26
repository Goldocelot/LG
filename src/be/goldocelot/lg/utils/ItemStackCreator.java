package be.goldocelot.lg.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemStackCreator {

	private String name;
	private int nbr;
	private Material mat;
	private byte b;
	
	public ItemStackCreator(String name, int nbr, Material mat, byte b) {
		this.name = name;
		this.nbr = nbr;
		this.mat = mat;
		this.b = b;
	}
	
	public ItemStack create() {
		if(this.b == 0) {
			ItemStack item = new ItemStack(this.mat, this.nbr);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(this.name);
			meta.spigot().setUnbreakable(true);
			item.setItemMeta(meta);
			return item;
		}else {
			ItemStack item = new ItemStack(this.mat, this.nbr, this.b);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(this.name);
			meta.spigot().setUnbreakable(true);
			item.setItemMeta(meta);
			return item;
		}
		
	}
}
