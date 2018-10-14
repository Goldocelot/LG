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
import org.bukkit.potion.PotionEffectType;

import be.goldocelot.lg.role.Role;
import be.goldocelot.lg.role.RoleConfig;
import be.goldocelot.lg.utils.ItemStackCreator;

/**
 * 
 * @author Nicolas Gerard (Goldocelot)
 * Class qui représente le role de voleur
 */
public class Voleur extends Role{

	private RoleConfig rConfig;
	
	public Voleur(RoleConfig rConfig) {
		this.rConfig = rConfig;
	}
	
	// Montant de vie initial du voleur
	@Override
	public double MaxHealth() {
		return 20;
	}

	// Joueur possédant le rôle de voleur
	@Override
	public List<Player> players() {
		YamlConfiguration config = rConfig.getNewConfiguration();
		List<Player> p = new ArrayList<>();
		for(String name : config.getConfigurationSection("Player").getKeys(false)) {
			if(config.getString("Player."+name).equals("Voleur")) {
				p.add(Bukkit.getPlayer(name));
			}
		}
		return p;
	}

	// Equipement de départ du voleur
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
		s.addEnchantment(Enchantment.DURABILITY, 3);
		e.add(s);
		ItemStack f = new ItemStackCreator(null, 64, Material.COOKED_FISH, (byte) 0).create();
		e.add(f);
		return e;
	}

	// Effet de potion du voleur
	@Override
	public List<PotionEffect> effect() {
		List<PotionEffect> pe = new ArrayList<>();
		PotionEffect a = new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0, false, false);
		pe.add(a);
		return pe;
	}

	@Override
	public List<String> rules() {
		List<String> rules = new ArrayList<>();
		rules.add("§8[§4LG§8]§r Vous êtes \"§7Voleur§r\" vous avez l'effet §5vitesse§r.");
		String voleur = "";
		YamlConfiguration config = rConfig.getNewConfiguration();
		for(String roleVoler : config.getConfigurationSection("Voleur").getKeys(false)) {
			if(voleur.equals("")) {
				voleur = config.getString("Voleur."+roleVoler);
			}else {
				voleur = voleur+", "+config.getString("Voleur."+roleVoler);;
			}
		}
		rules.add("§8[§4LG§8]§r Voici les rôles que vous pouvez incarner: "+voleur+".");
		rules.add("§8[§4LG§8]§r Vous devez utiliser la commande \"§6/voleur [Role]\"§r pour choisir celui que vous voulez incarner.");
		return rules;
	}

}
