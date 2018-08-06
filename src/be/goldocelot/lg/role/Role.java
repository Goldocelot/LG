package be.goldocelot.lg.role;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import be.goldocelot.lg.utils.ArmorContents;

public class Role{

	private int health;
	private ArrayList<Player> players;
	private ArrayList<ItemStack> stuff;
	private ArrayList<Player> team;
	
	public Role(ArrayList<Player> players, ArrayList<ItemStack> stuff, int vie, ArrayList<Player> team) {
		this.health = 20;
		this.players = players;
		this.stuff = stuff;
		this.team = team;
	}
	
	public void addPlayer(Player p) {
		this.players.add(p);
	}
	
	public void removePlayer(Player p) {
		this.players.remove(p);
	}
	
	public void setupPlayers() {
		for(Player role : this.players) {
			role.setMaxHealth(this.health);
			this.team.add(role);
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