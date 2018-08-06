package be.goldocelot.lg.role;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import be.goldocelot.lg.utils.ArmorContents;

public class Role {

	private int vie;
	private ArrayList<Player> contents;
	private ArrayList<ItemStack> stuff;
	
	public Role(ArrayList<Player> contents, ArrayList<ItemStack> stuff, int vie) {
		this.vie = 20;
		this.contents = contents;
		this.stuff = stuff;
	}
	
	public void addPlayer(Player p) {
		this.contents.add(p);
	}
	
	public void removePlayer(Player p) {
		this.contents.remove(p);
	}
	
	public void setupPlayers() {
		for(Player role : this.contents) {
			role.setMaxHealth(vie);
			for(ItemStack itemStack : this.stuff) {
				ArmorContents item = new ArmorContents(itemStack);
				if(item.isHelmet()) {
					role.getInventory().setHelmet(itemStack);
				}else if(item.isChestplate()) {
					role.getInventory().setChestplate(itemStack);
				}else if(item.isLeggings()) {
					role.getInventory().setLeggings(itemStack);
				}else if(item.isBoots()) {
					role.getInventory().setBoots(itemStack);
				}else if(item.isItem()) {
					role.getInventory().addItem(itemStack);
				}
			}
		}
	}
}