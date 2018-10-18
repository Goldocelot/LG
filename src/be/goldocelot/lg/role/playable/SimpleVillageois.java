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
 * Class qui représente le role de simple villageois
 */
public class SimpleVillageois extends Role {
	
	public SimpleVillageois(RoleConfig rConfig) {
		super(rConfig);
	}
	
	// Montant de vie initial des simples villageois
	@Override
	public double MaxHealth() {
		return 20d;
	}

	// Liste des joueurs possédant le rôle des simples villageois
	@Override
	public List<Player> players() {
		YamlConfiguration config = getrConfig().getNewConfiguration();
		List<Player> p = new ArrayList<>();
		for(String name : config.getConfigurationSection("Player").getKeys(false)) {
			if(config.getString("Player."+name).equals("Simple Villageois")) {
				p.add(Bukkit.getPlayer(name));
			}
		}
		return p;
	}

	// Equipement de départ des simples villageois
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
		l.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
		e.add(l);
		ItemStack b = new ItemStackCreator(null, 1, Material.LEATHER_BOOTS, (byte) 0).create();
		b.addEnchantment(Enchantment.DURABILITY, 3);
		e.add(b);
		ItemStack s = new ItemStackCreator(null, 1, Material.WOOD_SWORD, (byte) 0).create();
		s.addEnchantment(Enchantment.DURABILITY, 3);
		e.add(s);
		ItemStack f = new ItemStackCreator(null, 64, Material.COOKED_FISH, (byte) 0).create();
		e.add(f);
		return e;
	}

	// Effet de potion des simples villageois
	@Override
	public List<PotionEffect> effect() {
		List<PotionEffect> pe = new ArrayList<>();
		return pe;
	}

	@Override
	public List<String> rules() {
		List<String> rules = new ArrayList<>();
		rules.add("§8[§4LG§8]§r Vous êtes \"§aSimple Villageois§r\" vous devez gagner avec le §avillage§r.");
		rules.add("§8[§4LG§8]§r Pour gagner avec le §avillage§r vous devez éliminer toute les menaces pour celui-ci.");
		rules.add("§8[§4LG§8]§r En tant que \"§aSimple Villageois§§r\" vous disposez d'un pouvoir §9passif§r.");
		rules.add("§8[§4LG§8]§r §9Passif§r: Vous avez un §5plastron protection 1§r.");
		return rules;	
	}

}
