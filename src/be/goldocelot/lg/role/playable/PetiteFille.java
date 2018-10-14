package be.goldocelot.lg.role.playable;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import be.goldocelot.lg.role.Role;
import be.goldocelot.lg.role.RoleConfig;
import be.goldocelot.lg.utils.ItemStackCreator;

/**
 * 
 * @author Nicolas Gerard (Goldocelot)
 * Class qui représente le role de petite fille
 */
public class PetiteFille extends Role{
	
	private RoleConfig rConfig;
	
	public PetiteFille(RoleConfig rConfig) {
		this.rConfig = rConfig;
	}

	// Montant de vie initial de la petite fille
	@Override
	public double MaxHealth() {
		return 20d;
	}

	// Joueur possédant le rôle de petite fille
	@Override
	public List<Player> players() {
		YamlConfiguration config = rConfig.getNewConfiguration();
		List<Player> p = new ArrayList<>();
		for(String name : config.getConfigurationSection("Player").getKeys(false)) {
			if(config.getString("Player."+name).equals("Petite fille")) {
				p.add(Bukkit.getPlayer(name));
			}
		}
		return p;
	}

	// Equipement de départ de la petite fille
	@Override
	public List<ItemStack> equipement() {
		List<ItemStack> e = new ArrayList<>();
		ItemStack h = new ItemStackCreator(null, 1, Material.LEATHER_HELMET, (byte) 0).create();
		h.addEnchantment(Enchantment.DURABILITY, 3);
		e.add(h);
		ItemStack c = new ItemStackCreator(null, 1, Material.LEATHER_CHESTPLATE, (byte) 0).create();
		c.addEnchantment(Enchantment.DURABILITY, 3);
		e.add(c);
		ItemStack l = new ItemStackCreator(null, 1, Material.LEATHER_LEGGINGS, (byte) 0).create();
		l.addEnchantment(Enchantment.DURABILITY, 3);
		e.add(l);
		ItemStack b = new ItemStackCreator(null, 1, Material.LEATHER_BOOTS, (byte) 0).create();
		b.addEnchantment(Enchantment.DURABILITY, 3);
		e.add(b);
		ItemStack s = new ItemStackCreator(null, 1, Material.WOOD_SWORD, (byte) 0).create();
		s.addEnchantment(Enchantment.KNOCKBACK, 1);
		e.add(s);
		ItemStack f = new ItemStackCreator(null, 64, Material.COOKED_FISH, (byte) 0).create();
		e.add(f);
		return e;
	}

	// Effet de potion de la petite fille
	@Override
	public List<PotionEffect> effect() {
		List<PotionEffect> pe = new ArrayList<>();
		return pe;
	}

	@Override
	public List<String> rules() {
		List<String> rules = new ArrayList<>();
		rules.add("§8[§4LG§8]§r Vous êtes \"§aPetite-Fille§r\" vous devez gagner avec le §avillage§r.");
		rules.add("§8[§4LG§8]§r Pour gagner avec le §avillage§r vous devez éliminer toute les menaces pour celui-ci.");
		rules.add("§8[§4LG§8]§r En tant que \"§aPetite-Fille§r\" vous disposez de deux pouvoirs §9passif§r.");
		rules.add("§8[§4LG§8]§r §9Passif§r: Vous avez une §5épée recule 1§r et chaque §6matin§r votre ourson en peluche vous chuchotera des choses qui se sont passé la nuit précédente§r.");
		return rules;	
	}

}
