package be.goldocelot.lg.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * 
 * @author Nicolas Gerard (Goldocelot)
 * Class utilitaire qui permet de créer plus facilement des items
 */
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
	
	// Méthode qui créé l'objet
	public ItemStack create() {
		// Si le byte de l'item est 0
		if(this.b == 0) {
			// On créé un item avec le bon matériel, le bon nombre en ignorant le byte
			ItemStack item = new ItemStack(this.mat, this.nbr);
			// On lui donne le bon nom
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(this.name);
			meta.spigot().setUnbreakable(true);
			item.setItemMeta(meta);
			return item;
		}else {
			// Sinon on créé un item avec le bon matériel, le bon nombre en prenant en compte le byte
			ItemStack item = new ItemStack(this.mat, this.nbr, this.b);
			ItemMeta meta = item.getItemMeta();
			// On lui donne le bon nom
			meta.setDisplayName(this.name);
			meta.spigot().setUnbreakable(true);
			item.setItemMeta(meta);
			return item;
		}
		
	}
}
